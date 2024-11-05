import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

class CommandProcessor {
    private List<String> errorLog;

    public CommandProcessor() {
        errorLog = new ArrayList<>();
    }

    public void createProcess(String command) {
        String[] commands = command.split(" ");
        switch (commands[0].toLowerCase()) {
            case "filedump":
                if (commands.length > 1) {
                    filedump(commands[1]);
                } else {
                    System.out.println("Usage: filedump <filename>");
                }
                break;
            case "copyfile":
                if (commands.length > 2) {
                    copyfile(commands[1], commands[2]);
                } else {
                    System.out.println("Usage: copyfile <sourcefile> <destfile>");
                }
                break;
            case "createfile":
                if (commands.length > 1) {
                    createfile(commands[1]);
                } else {
                    System.out.println("Usage: createfile <filename>");
                }
                break;
            default:
                executeExternalCommand(command);
                break;
        }
    }

    private void executeExternalCommand(String command) {
        List<String> input = Arrays.asList(command.split(" "));
        ProcessBuilder processBuilder = new ProcessBuilder(input);
        BufferedReader bufferedReader = null;
        try {
            Process process = processBuilder.start();
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException ioe) {
            System.err.println("Error running command: " + command);
            logError(command);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }

    private void filedump(String filename) {
        try {
            String content = Files.readString(Path.of(filename));
            System.out.println(content);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private void copyfile(String sourceFile, String destFile) {
        try {
            Files.copy(Path.of(sourceFile), Path.of(destFile), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error copying file: " + e.getMessage());
        }
    }

    private void createfile(String filename) {
        try {
            boolean result = new File(filename).createNewFile();
            if (result) {
                System.out.println("File created successfully: " + filename);
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }

    private void logError(String command) {
        errorLog.add(command);
    }

    public void showErrorLog() {
        if (errorLog.isEmpty()) {
            System.out.println("No error log found.");
        } else {
            System.out.println("Errors Log:");
            for (String error : errorLog) {
                System.out.println(error);
            }
        }
    }
}

class ProcessThread implements Runnable {
    private String command;
    private CommandProcessor processor;

    public ProcessThread(String command, CommandProcessor processor) {
        this.command = command;
        this.processor = processor;
    }

    public void run() {
        processor.createProcess(command);
    }
}

class Stopwatch implements Runnable {
    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        try {
            while (elapsedTime < 60000) {
                Thread.sleep(10);
                elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Stopwatch thread. Elapsed time: " + elapsedTime / 1000.0 + " seconds");
            }
            System.out.println("Main thread. Finished stopwatch thread.");
        } catch (InterruptedException error) {
            System.out.println("Main thread. Stopwatch interrupted.");
        }
    }
}

class TestProcessBuilder {
    private CommandProcessor processor;

    public TestProcessBuilder() {
        processor = new CommandProcessor();
    }

    public void startShell() {
        String commandLine;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");

        while (true) {
            System.out.print("jsh> ");
            commandLine = scanner.nextLine();

            if (commandLine.equals(" ")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) {
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            } else if (commandLine.toLowerCase().equals("showerrlog")) {
                processor.showErrorLog();
            } else if (commandLine.toLowerCase().equals("startstopwatch")) {
                Thread stopwatchThread = new Thread(new Stopwatch());
                stopwatchThread.start();
                try {
                    stopwatchThread.join();
                } catch (InterruptedException error) {
                    error.printStackTrace();
                }
            } else {
                Thread processThread = new Thread(new ProcessThread(commandLine, processor));
                processThread.start();
            }
        }
    }

    public static void main(String[] args) {
        TestProcessBuilder shell = new TestProcessBuilder();
        shell.startShell();
    }
}
