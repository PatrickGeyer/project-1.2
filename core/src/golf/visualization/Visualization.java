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
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;


public class Visualization implements ApplicationListener {
    public Environment environment;
    public PerspectiveCamera cam;
    public Model model;
    public ModelBatch modelBatch;
    public ModelInstance instance;
    public CameraInputController camController;
    public Course c;
    public PhysicsEngine p;

    public Mesh courseMesh;
    ShaderProgram shader;
    SpriteBatch batch;


    public Visualization(Course c, PhysicsEngine p) {
        this.c = c;
        this.p = p;
    }

    public static void main(String[] args) {
        Visualization v = new Visualization(new Course(), new EulerSolver(0.01));
    }


	@Override
    public void create () {
        courseMesh = createCourseMesh();
        shader = Visualization.createMeshShader();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		modelBatch = new ModelBatch();

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f, 5f, 5f, 
            new Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position | Usage.Normal);
        instance = new ModelInstance(model);
    }

	@Override
    public void dispose () {
        modelBatch.dispose();
        model.dispose();    
    }

    public void take_shot(Vector2d v) {
        // c.ball.acceleration.add(v);
    }  

    @Override
    public void render () {
        // Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //no need for depth...
        Gdx.gl.glDepthMask(false);

        //enable blending, for alpha
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shader.begin();
        shader.setUniformMatrix("u_projTrans", cam.combined);
        shader.setUniformi("u_texture", 0);

        courseMesh.render( shader, GL20.GL_TRIANGLES, 0, 100 * 100);
        shader.end();
        
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

    public Mesh createCourseMesh() {

        MeshBuilder builder = new MeshBuilder();
        builder.begin(Usage.Position | Usage.Normal, GL20.GL_TRIANGLES);
        for(float y = 0; y < 10; y+= 0.1) {
            for(float x = 0; x < 10; x+=0.1) {
                builder.vertex(new Vector3(y, x, 0), new Vector3(0,0,0), new Color(255, 255, 255, 1), new Vector2(0,0));
            }
        }

        return builder.end();
    }

    public static final String VERT_SHADER =  
			"attribute vec2 a_position;\n" +
			"attribute vec4 a_color;\n" +			
			"uniform mat4 u_projTrans;\n" + 
			"varying vec4 vColor;\n" +			
			"void main() {\n" +  
			"	vColor = a_color;\n" +
			"	gl_Position =  u_projTrans * vec4(a_position.xy, 0.0, 1.0);\n" +
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