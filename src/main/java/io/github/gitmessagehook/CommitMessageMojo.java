package io.github.gitmessagehook;

import io.github.gitmessagehook.exceptions.GitFolderNotFoundException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.IOException;

@Mojo(name = "commit-message", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class CommitMessageMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    MavenProject project;

    @Parameter(property = "git-directory")
    String gitDirectory;

    public void execute() throws MojoExecutionException {
        final File gitFile = getGitFile();
        GitFolderNotFoundException.throwsIf(!gitFile.exists(), gitFile.getAbsolutePath());

        printFln("project %s", project);
        printFln("git-directory: %s", gitDirectory);
        printFln("gitPath: %s", gitFile.getAbsolutePath());
    }

    private File getGitFile() {
        try {
            File basedir = project.getBasedir();
            String gitPath = basedir.getAbsolutePath() + "/";
            if (gitDirectory != null && gitDirectory.length() > 0) {
                 gitPath += gitDirectory;
            }
            gitPath += ".git/hooks";
            return new File(gitPath).getCanonicalFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printFln(String format, Object... args) {
        System.out.printf(format + "\n", args);
    }
}
