package actions.generateJavaBean;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.wm.WindowManager;

/**
 * 根据类名和文本生成Java Bean文件
 */
public class GenerateJavaBeanAction extends AnAction {

    private GenerateJavaBeanDialog.OnClickListener mClickListener = new GenerateJavaBeanDialog.OnClickListener() {
        @Override
        public void onGenerate(String str, String member, boolean serializable) {

        }

        @Override
        public void onCancel() {

        }
    };

    @Override
    public void actionPerformed(AnActionEvent e) {
        GenerateJavaBeanDialog dialog = new GenerateJavaBeanDialog();
        dialog.setOnClickListener(mClickListener);
        dialog.setTitle("Generate Java Bean By String");
        //默认设置Serializable为false，即不产生：“private static final long serialVersionUID = 1L;”
        dialog.setCbSerializable(false);
        //自动调整对话框大小
        dialog.pack();
        //设置对话框跟随当前windows窗口
        dialog.setLocationRelativeTo(WindowManager.getInstance().getFrame(e.getProject()));
        dialog.setVisible(true);
    }

}
