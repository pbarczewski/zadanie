package com.company.binaryManager;

public class LoadBinaryFile implements Activity {
    private final BinaryFile binaryFile;

    public LoadBinaryFile(BinaryFile binaryFile) {
        this.binaryFile = binaryFile;
    }

    @Override
    public void execute() {
        binaryFile.loadFile();
    }
}
