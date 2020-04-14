package golf.visualization;

import golf.physics.*;
import golf.course.*;

import com.badlogic.gdx.ApplicationListener;
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


public class Visualization implements ApplicationListener {
    public Environment environment;
    public PerspectiveCamera cam;
    public ModelInstance ball;
    public ModelBatch modelBatch;

    public CameraInputController camController;
    public PuttingCourse c;
    public PhysicsEngine p;

    public Mesh courseMesh;
    ShaderProgram shader;
    SpriteBatch batch;
    

    public Visualization(PuttingCourse c, PhysicsEngine p) {
        this.c = c;
        this.p = p;
    }

    public static void main(String[] args) {
        Visualization v = new Visualization(new PuttingCourse(), new EulerSolver(0.01));
    }

	@Override
    public void create () {

        // take_shot();
        modelBatch = new ModelBatch();

        courseMesh = createCourseMesh(20, 20, 0.25);
        shader = Visualization.createMeshShader();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1.0f));
        environment.add(new PointLight().set(0.8f, 0.8f, 0.8f, 2f, 0f, 0f, 10f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 50f, 0f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        InputMultiplexer multiplexer = new InputMultiplexer();

        camController = new CameraInputController(cam);
        multiplexer.addProcessor(new InputAdapter() {
            public boolean pan(float x, float y, float deltaX, float deltaY) {
                // System.
                return false;
            }

            public boolean panStop(float x, float y, int pointer, int button) {
                System.out.println(x + ", " + y);
                return false;
            }
        });
        multiplexer.addProcessor(camController);

        Gdx.input.setInputProcessor(multiplexer);

        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createSphere(.4f, .4f, .4f, 24, 24, 
            new Material(ColorAttribute.createDiffuse(Color.WHITE)),
            Usage.Position | Usage.Normal | Usage.TextureCoordinates);
        ball = new ModelInstance(model);
    }

	@Override
    public void dispose () {
    }

    public void take_shot(Vector2d v) {
        // c.ball.acceleration.add(v);
    }  

    @Override
    public void render () {

        Gdx.gl.glClearColor(135/255f, 206/255f, 235/255f, 1);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        Gdx.gl.glDepthMask(false);

        //enable blending, for alpha
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shader.begin();
        shader.setUniformMatrix("u_projTrans", cam.combined);
        shader.setUniformi("u_texture", 0);

        courseMesh.render(shader, GL20.GL_TRIANGLES);

        shader.end();

        modelBatch.begin(cam);
        modelBatch.render(ball, environment);
        modelBatch.end();
        
        //re-enable depth to reset states to their default
	    Gdx.gl.glDepthMask(true);
    }

    @Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
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

                double height = c.height.evaluate(x, y);

                pos1 = new Vector3 ((float) x, (float) y, (float) (c.height.evaluate(x, y)));
                pos2 = new Vector3 ((float) x, (float) (y + division), (float) (c.height.evaluate(x, y + division)));
                pos3 = new Vector3 ((float) (x + division), (float) (y + division), (float) (c.height.evaluate(x + division, y + division)));
                pos4 = new Vector3 ((float) (x + division), (float) y, (float) (c.height.evaluate(x + division, y)));

                System.out.println(pos1);
                System.out.println(pos2);
                System.out.println(pos3);
                System.out.println(pos4);

                System.out.println("Grad: " + c.height.gradient(x, y).get_y());

                nor1 = new Vector3(
                    (float) - c.height.gradient(x, y).get_x(), 
                    1, 
                    (float) - c.height.gradient(x, y).get_y()
                );
                nor2 = new Vector3(
                    (float) - c.height.gradient(x, y).get_x(), 
                    1, 
                    (float) - c.height.gradient(x, y + division).get_y()
                );
                nor3 = new Vector3(
                    (float) - c.height.gradient(x + division, y + division).get_x(), 
                    1, 
                    (float) - c.height.gradient(x + division, y + division).get_y()
                );
                nor4 = new Vector3(
                    (float) - c.height.gradient(x + division, y).get_x(), 
                    1, 
                    (float) - c.height.gradient(x, y).get_y()
                );

                // System.out.println(nor1);
                // System.out.println(nor2);
                // System.out.println(nor3);
                // System.out.println(nor4);

                Color c = new Color(0, 0, 0, 1);

                if(height < 0) {
                    // Water
                    c = new Color(0, 0, 1, 1);
                } else {
                    // Grass
                    float whiteness = Math.max( (float) height / 10, 0);
                    c = new Color(whiteness, 1, whiteness, 1);
                }

                System.out.println("(" + x + ", " + y + ")");

                System.out.println(height);

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