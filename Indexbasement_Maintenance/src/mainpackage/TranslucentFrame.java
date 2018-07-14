package mainpackage;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

//import com.sun.awt.AWTUtilities;

/**
 * ��Ļ���½ǳ��ֽ������Ե���ʾ��
 * ʹ�õ���JDK1.6�������Ե�͸�����壬���Ա���Ҫʹ��JDK1.6�����ϰ汾��JDK
 * �������£�
 * 1.�������ʱ������
 * 2.ͣ��һ���ʱ��֮����Զ���ģ��ֱ����ʧ
 * 3.����رհ�ť����ģ��ֱ����ʧ
 * 4.��ʾ����֧��html��ǩ
 *
 */
@SuppressWarnings("restriction")
public class TranslucentFrame implements Runnable{

    JFrame frame;
    JLabel label1;
    JEditorPane editorPane1;

    private int width;//������
    private int height;//����߶�
    private int stayTime;//����ʱ��
    private String title,message;//��Ϣ����,����
    private int style;//������ʽ
    static{
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
    }
    /**
     * �������Ե���ʾ��
     * 
     * @param width     ��ʾ����
     * @param height    ��ʾ��߶�
     * @param stayTime  ��ʾ��ͣ��ʱ��
     * @param style     ��ʾ�����ʽ
     * ����Ϊ��ʽ��ѡֵ��
     * 0    NONE                  ��װ�Σ���ȥ����������
     * 1    FRAME                 ��ͨ���ڷ��
     * 2    PLAIN_DIALOG          �򵥶Ի�����
     * 3    INFORMATION_DIALOG    ��Ϣ�Ի�����
     * 4    ERROR_DIALOG          ����Ի�����
     * 5    COLOR_CHOOSER_DIALOG  ʰɫ���Ի�����
     * 6    FILE_CHOOSER_DIALOG   �ļ�ѡ��Ի�����
     * 7    QUESTION_DIALOG       ����Ի�����
     * 8    WARNING_DIALOG        ����Ի�����
     * @param title     ��ʾ�����
     * @param message   ��ʾ�����ݣ�֧��html��ǩ��
     */
    public TranslucentFrame(int width,int height,int stayTime,int style,String title,String message){
        this.width=width;
        this.height=height;
        this.stayTime=stayTime;
        this.style=style;
        this.title=title;
        this.message=message;
    }

    /**
     * �������Ե���ʾ��
     * 
     * @param style     ��ʾ����ʽͬ��
     * @param title     ��ʾ�����
     * @param message   ��ʾ������
     */
    public TranslucentFrame(int style,String title,String message){
        this.width=150;
        this.height=80;
        this.stayTime=2;
        this.style=style;
        this.title=title;
        this.message=message;
    }

//    public static void main(String[] args) {
//        String title="������ʾ��";
//        String message="<strong>JDK1.6�����Բ���</strong><br>��͸�����塷<br>www.oschina.net<br>��Դ�й�";
////      Runnable translucent=new TranslucentFrame(250,180,10,4,title,message);
//        Runnable translucent=new TranslucentFrame(2,title,message);
//        Thread thread=new Thread(translucent);
//        thread.start();
//    }

    @SuppressWarnings("restriction")
	public void print(){
        frame=new JFrame();
        editorPane1=new JEditorPane();
        editorPane1.setEditable(false);//���ɱ༭
        editorPane1.setContentType("text/html");//���༭������Ϊ֧��html�ı༭��ʽ
        editorPane1.setText(message);
        frame.add(editorPane1);
        frame.setTitle(title);
        //���ô����λ�ü���С
        int x=Toolkit.getDefaultToolkit().getScreenSize().width-Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration()).right-width-5;
        int y=Toolkit.getDefaultToolkit().getScreenSize().height-Toolkit.getDefaultToolkit().getScreenInsets(frame.getGraphicsConfiguration()).bottom-height-5;
        frame.setBounds(x, y, width, height);
        frame.setUndecorated(true); // ȥ�����ڵ�װ��
        frame.getRootPane().setWindowDecorationStyle(style ); //������ʽ 
//      frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG ); //������ʽ 
//        AWTUtilities.setWindowOpacity(frame, 0.01f);//��ʼ��͸����
        frame.setVisible(true);
        frame.setAlwaysOnTop(true);//�����ö�
        //��ӹرմ��ڵļ���
        frame.addWindowListener(new WindowAdapter() {  
            public void windowClosing(WindowEvent e) {  
                hide();
            }  
        });   
    }

    /**
     * �����𽥱�����
     *
     */
    @SuppressWarnings("restriction")
	public void show(){
        for (int i = 0; i < 50; i++) {
            try {
                Thread.sleep(20);
            } catch (Exception e) {}
//            AWTUtilities.setWindowOpacity(frame, i*0.02f);
        }
    }

    /**
     * �����𽥱䵭ֱ����ʧ
     *
     */
    @SuppressWarnings({ "restriction", "deprecation" })
	public void hide(){
        float opacity=100;
        while(true){
            if(opacity<2){
                System.out.println();
                break;
            }
            opacity=opacity-2;
//            AWTUtilities.setWindowOpacity(frame, opacity/100);
            try {
                Thread.sleep(20);
            } catch (Exception e1) {}
        }
      frame.hide();;
    }

    public void run(){
        print();
        show();
        try {
            Thread.sleep(stayTime*1000);
        } catch (Exception e) {}
        hide();
    }
}