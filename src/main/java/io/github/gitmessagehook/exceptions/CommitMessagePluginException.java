package io.github.gitmessagehook.exceptions;

public class CommitMessagePluginException extends RuntimeException {
    public CommitMessagePluginException(String message) {
        super(message);
    }
}
