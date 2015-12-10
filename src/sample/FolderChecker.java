package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by stefa on 10.12.2015.
 */
public class FolderChecker extends Thread {

    ArrayList<File> files = new ArrayList<>();

    @Override
    public void run() {
        File file = new File("C:\\FlashCard\\tmp_files");
        Path path = file.toPath();
        if (file.isDirectory()) {
            WatchService ws = null;
            try {
                ws = path.getFileSystem().newWatchService();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                path.register(ws, StandardWatchEventKinds.ENTRY_CREATE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            WatchKey watch = null;
            while (true) {
                System.out.println("Watching directory: " + file.getPath());
                try {
                    watch = ws.take();
                } catch (InterruptedException ex) {
                    System.err.println("Interrupted");
                }
                List<WatchEvent<?>> events = watch.pollEvents();
                watch.reset();
                for (WatchEvent<?> event : events) {
                    WatchEvent.Kind<Path> kind = (WatchEvent.Kind<Path>) event.kind();
                    Path context = (Path) event.context();
                    if (kind.equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                        File folder = new File("C:\\FlashCard\\tmp_files");
                        File[] listOfFiles = folder.listFiles();
                        System.out.println("Created: " + path.getFileName());
                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (listOfFiles[i].isFile()) {
                                files.add(listOfFiles[i]);
                                System.out.println("File " + listOfFiles[i].getName());
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<File> getFiles(){
        return files;
    }
}
