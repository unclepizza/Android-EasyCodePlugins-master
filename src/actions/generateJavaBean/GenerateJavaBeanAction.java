package actions.generateJavaBean;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.apache.http.util.TextUtils;

/**
 * 根据类名和文本生成Java Bean文件
 */
public class GenerateJavaBeanAction extends AnAction {
    private AnActionEvent actionEvent;

    private GenerateJavaBeanDialog.OnClickListener mClickListener = new GenerateJavaBeanDialog.OnClickListener() {

        @Override
        public void onGenerate(String className, String classPackage, String pasteStr, boolean serializable, String
                member) {
            String result;
            try {
                result = generateFile(classPackage, className, pasteStr, serializable, member);
            } catch (Exception e) {
                result = e.getMessage();
            }
            if (!TextUtils.isEmpty(result)) {
                Messages.showMessageDialog(result, "Error", Messages.getInformationIcon());
            }
        }

        @Override
        public void onCancel() {
            //nothing…
        }
    };

    private String generateFile(String classPackage, String className, String pasteStr, boolean serializable, String member) {
        if (actionEvent == null) {
            return "";
        }
        //当前编辑的文件，可能为null
        PsiFile psiFile = actionEvent.getData(LangDataKeys.PSI_FILE);
        Editor editor = actionEvent.getData(PlatformDataKeys.EDITOR);
        //当前工程
        Project project = editor.getProject();
        PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());

        return "";
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        this.actionEvent = e;
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
