package golf.visualization.screen;

import golf.physics.*;
import golf.course.*;
import golf.course.object.*;
import golf.inputoutput.*;
import golf.visualization.Golf;
import golf.visualization.screen.BallInfoPanel;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Input.Keys;

public class Visualization implements Screen {
    public Golf parent;
    public BallInfoPanel infoPanel;
    public Environment environment;
    public PerspectiveCamera cam;
    public List<ModelInstance> balls = new ArrayList();
    public List<ModelInstance> trees = new ArrayList();
    public ModelInstance arrow;
    public ModelInstance flag;
    public ModelBatch modelBatch;
    public ModelBuilder modelBuilder;

    public CameraInputController camController;
    public PuttingSimulator simulation;

    public Mesh courseMesh;
    ShaderProgram shader;
    SpriteBatch batch;

    private double currentTime;
    private float step = 1.0f / 60.0f;
    

    public Visualization(Golf parent, PuttingSimulator simulation) {
        this.simulation = simulation;
        this.parent = parent;
    }

    public void hide() {

    }

    public void show() {

        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();

        courseMesh = createCourseMesh(this.simulation.course.flag.x + 10, this.simulation.course.flag.y + 10, this.simulation.course.flag.len() / 50);
        shader = Visualization.createMeshShader();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        environment.add(new PointLight().set(0.8f, 0.8f, 0.8f, 2f, 0f, 0f, 10f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, -50f, 50f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        InputMultiplexer multiplexer = new InputMultiplexer();

        camController = new CameraInputController(cam);
        multiplexer.addProcessor(new InputAdapter() {

            public Vector2 startClick;
            public Vector2 endClick;

            public Ball getCurrentBall() {
                return simulation.course.getBalls().get(0);
            }

            public Vector2 calculateVector(Vector2 start, Vector2 end) {
                return new Vector2(end.x - start.x, end.y - start.y);
            }
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Ball b = getCurrentBall();
                Vector2 direction = calculateVector(startClick, new Vector2(screenX, screenY));
                if(direction.len() > 0) {
                    Model m = modelBuilder.createArrow(b.position.x, b.position.y, b.position.z, b.position.x + direction.x, b.position.y + direction.y, b.position.z, 0.1f, 0.1f, 5, 
                    GL20.GL_TRIANGLES, new Material(ColorAttribute.createDiffuse(Color.RED)),
                    Usage.Position | Usage.Normal);
                    arrow = new ModelInstance(m);
                }
                return false;
            }
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                arrow = null;
                endClick = new Vector2(screenX, screenY);
                simulation.take_shot(getCurrentBall(), calculateVector(startClick, endClick));
                return true;
            }
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                startClick = new Vector2(screenX, screenY);
                return true;
            }
            public boolean keyTyped (char c) {
                System.out.println(c);
                if(c == 's') {
                    InputOutput io = new InputOutput();
                    io.saveDialog(simulation.course);
                }
                System.out.println(c);
                return false;
            }
            public boolean keyDown (int keycode) {

                switch (keycode) {
                    case Keys.BACKSPACE:
                        parent.changeScreen(Golf.MENU);
                        break;
                }
                return false;
            }
        });
        multiplexer.addProcessor(camController);

        Gdx.input.setInputProcessor(multiplexer);

        for(Ball b : this.simulation.course.getBalls()) {
            Model model = modelBuilder.createSphere((float) b.radius, (float) b.radius, (float) b.radius, 24, 24, 
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);
            this.balls.add(new ModelInstance(model));
        }
        for(Obstacle o : this.simulation.course.getObstacles()) {
            Model model = modelBuilder.createBox(o.dimensions.x, o.dimensions.y, o.dimensions.z,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                Usage.Position | Usage.Normal | Usage.TextureCoordinates);
            this.trees.add(new ModelInstance(model));
            o.position.z = (float) this.simulation.course.height.evaluate(o.position.x, o.position.y);
            this.trees.get(this.trees.size() - 1).transform.setTranslation(
                o.position
            );
        }

        Model flagM = modelBuilder.createArrow(
            simulation.course.flag.x, 
            simulation.course.flag.y, 
            (float) simulation.course.height.evaluate(simulation.course.flag), 
            simulation.course.flag.x, 
            simulation.course.flag.y, 
            (float) simulation.course.height.evaluate(simulation.course.flag) + 5, 
            0.1f, .5f, 5, 
                    GL20.GL_TRIANGLES, new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                    Usage.Position | Usage.Normal);
        flag = new ModelInstance(flagM);


        infoPanel = new BallInfoPanel(this.simulation.course.getBalls().get(0));

    }

    public void dispose () {
    }

    public void render (float f) {
        
        // super.render();
        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        this.updatePhysics();

        Gdx.gl.glDepthMask(false);

        //enable blending, for alpha
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        for(int i = 0; i < this.trees.size(); i++) {
            modelBatch.render(this.trees.get(i), environment);
        }

        shader.begin();
        shader.setUniformMatrix("u_projTrans", cam.combined);
        shader.setUniformi("u_texture", 0);

        courseMesh.render(shader, GL20.GL_TRIANGLES);

        shader.end();

        modelBatch.begin(cam);
        if(arrow != null) 
            modelBatch.render(arrow, environment);
        modelBatch.render(flag, environment);
        modelBatch.end();
        
        //re-enable depth to reset states to their default
	    Gdx.gl.glDepthMask(true);

        for(int i = 0; i < this.simulation.course.getBalls().size(); i++) {
            this.balls.get(i).transform.setTranslation(
                this.simulation.course.getBalls().get(i).position
            );
            modelBatch.render(this.balls.get(i), environment);
        }
        

        infoPanel.draw();
    }

    public void updatePhysics() {
        double newTime = TimeUtils.millis() / 1000.0;
        double frameTime = Math.min(newTime - currentTime, 0.25);
        float deltaTime = (float)frameTime;

        currentTime = newTime;

        this.simulation.step(0.01);
    }

	public void resize(int width, int height) {
	}

	public void pause() {
	}

	public void resume() {
	}

    public Mesh createCourseMesh(double gridCol, double gridRow, double division) {
        ModelBuilder mb = new ModelBuilder();
        mb.begin();
        MeshPartBuilder b = mb.part("terrain", GL20.GL_TRIANGLES, Usage.Position | Usage.Normal | Usage.ColorPacked, new Material(ColorAttribute.createDiffuse(Color.WHITE)));
        Vector3 pos1,pos2,pos3,pos4;
        Vector3 nor1,nor2,nor3,nor4;
        MeshPartBuilder.VertexInfo v1,v2,v3,v4;
        for(double x = 0; x <= gridCol; x += division) {
            for(double y = 0; y <= gridRow; y += division) {

                double height = this.simulation.course.height.evaluate(x, y);

                pos1 = new Vector3 ((float) x, (float) y, (float) (this.simulation.course.height.evaluate(x, y)));
                pos2 = new Vector3 ((float) x, (float) (y + division), (float) (this.simulation.course.height.evaluate(x, y + division)));
                pos3 = new Vector3 ((float) (x + division), (float) (y + division), (float) (this.simulation.course.height.evaluate(x + division, y + division)));
                pos4 = new Vector3 ((float) (x + division), (float) y, (float) (this.simulation.course.height.evaluate(x + division, y)));

                nor1 = new Vector3(
                    (float) - this.simulation.course.height.gradient(x, y).get_x(), 
                    1, 
                    (float) - this.simulation.course.height.gradient(x, y).get_y()
                );
                nor2 = new Vector3(
                    (float) - this.simulation.course.height.gradient(x, y).get_x(), 
                    1, 
                    (float) - this.simulation.course.height.gradient(x, y + division).get_y()
                );
                nor3 = new Vector3(
                    (float) - this.simulation.course.height.gradient(x + division, y + division).get_x(), 
                    1, 
                    (float) - this.simulation.course.height.gradient(x + division, y + division).get_y()
                );
                nor4 = new Vector3(
                    (float) - this.simulation.course.height.gradient(x + division, y).get_x(), 
                    1, 
                    (float) - this.simulation.course.height.gradient(x, y).get_y()
                );

                Color c = new Color(0, 0, 0, 1);

                if(height < 0) {
                    // Water
                    c = new Color(0, 0, 1, 1);
                } else {
                    // Grass
                    float whiteness = Math.max( (float) height / 10, 0);
                    c = new Color(whiteness, 1, whiteness, 1);
                }
		
		if (x>10 && x<17 && y>19){
			// Sand
                    c = new Color( 1, 1, 0, 1);
                }

                v1 = new MeshPartBuilder.VertexInfo().setPos(pos1).setNor(nor1).setCol(c).setUV(0.0f, 0.0f);
                v2 = new MeshPartBuilder.VertexInfo().setPos(pos2).setNor(nor2).setCol(c).setUV(0.0f, 0.5f);
                v3 = new MeshPartBuilder.VertexInfo().setPos(pos3).setNor(nor3).setCol(c).setUV(0.5f, 0.0f);
                v4 = new MeshPartBuilder.VertexInfo().setPos(pos4).setNor(nor4).setCol(c).setUV(0.5f, 0.5f);

                b.rect(v1, v2, v3, v4);
            }
        }
        return mb.end().meshes.get(0);
    }
    public static final String VERT_SHADER =  
			"attribute vec3 a_position;\n" +
			"attribute vec4 a_color;\n" +			
			"uniform mat4 u_projTrans;\n" + 
			"varying vec4 vColor;\n" +			
			"void main() {\n" +  
			"	vColor = a_color;\n" +
			"	gl_Position =  u_projTrans * vec4(a_position.xyz, 1.0);\n" +
			"}";
	
	public static final String FRAG_SHADER = 
            "#ifdef GL_ES\n" +
            "precision mediump float;\n" +
            "#endif\n" +
			"varying vec4 vColor;\n" + 			
			"void main() {\n" +
			"	gl_FragColor = vColor;\n" + 
			"}";
	
	protected static ShaderProgram createMeshShader() {
		ShaderProgram.pedantic = false;
		ShaderProgram shader = new ShaderProgram(VERT_SHADER, FRAG_SHADER);
		String log = shader.getLog();
		if (!shader.isCompiled())
			throw new GdxRuntimeException(log);		
		if (log!=null && log.length()!=0)
			System.out.println("Shader Log: "+log);
		return shader;
	}


}
