package FileMock;

import org.mockito.Mockito;

import java.io.File;
import java.nio.file.Files;

import static org.mockito.Mockito.when;

public class FileMock extends File{//TODO: Del? Nobody uses that

    private File [] filesListing;
    private String absolutePath;

    @Override
    public File[] listFiles() {
        return filesListing;
    }
    @Override
    public String getAbsolutePath() {
        return absolutePath;
    }

    public FileMock(String absolutePath)
    {
        super(absolutePath);
    }

    public static final class Builder
    {
        private File [] filesListing;
        private String absolutePath;

        private FileMock fileMock = Mockito.mock(FileMock.class);

        public Builder(String absolutePath)
        {
            this.absolutePath = absolutePath;
            //when(fileMock.absolutePath).thenReturn(absolutePath);
        }

        public Builder filesListing(File[] filesListing)
        {
            this.filesListing = filesListing;
            //when(fileMock.filesListing).thenReturn(filesListing);
            return this;
        }

        public FileMock build()
        {
            fileMock.absolutePath = this.absolutePath;
            fileMock.filesListing = this.filesListing;

            return fileMock;
        }
    }


}
