package com.company.binaryManager;

public class SaveBinaryFile implements Activity {
    private final BinaryFile binaryFile;

    public SaveBinaryFile(BinaryFile binaryFile) {
        this.binaryFile = binaryFile;
    }

    @Override
    public void execute() {
        binaryFile.saveFile();
    }
}
