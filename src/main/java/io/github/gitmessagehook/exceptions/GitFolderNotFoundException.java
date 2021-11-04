package io.github.gitmessagehook.exceptions;

public class GitFolderNotFoundException extends CommitMessagePluginException {
    private GitFolderNotFoundException(String message) {
        super(message);
    }

    public static void throwsIf(boolean condition, String path) {
        if (condition) {
            throw new GitFolderNotFoundException("Git path not found: " + path);
        }
    }
}
