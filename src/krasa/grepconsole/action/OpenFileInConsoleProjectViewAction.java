package krasa.grepconsole.action;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

/**
 * @author Vojtech Krasa
 */
public class OpenFileInConsoleProjectViewAction extends OpenFileInConsoleAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		final Project project = getEventProject(e);
		if (project == null) return;
		
		VirtualFile[] data = e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				VirtualFile virtualFile = data[i];
				if (virtualFile != null && !virtualFile.isDirectory()) {
					final String path = virtualFile.getPath();
					openFileInConsole(project, new File(path));
				}
			}
		}
	}

	@Override
	public void update(AnActionEvent e) {
		super.update(e);
		boolean show = false;
		VirtualFile[] data = e.getData(PlatformDataKeys.VIRTUAL_FILE_ARRAY);
		if (data != null) {
			for (int i = 0; i < data.length; i++) {
				VirtualFile virtualFile = data[i];
				if (virtualFile != null && !virtualFile.isDirectory()) {
					show = true;
				}
			}
		}
		e.getPresentation().setVisible(show);
	}
}
