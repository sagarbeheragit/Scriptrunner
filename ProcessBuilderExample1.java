import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBuilderExample1 {

    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        // Windows
        processBuilder.command("bash", "-c", "aws cloudformation package --template-file sam.yaml --output-template-file output-sam.yaml --s3-bucket aws-serverless-springboot-app-001 ; aws cloudformation deploy --template-file output-sam.yaml --stack-name ServerlessSpringBootApp --capabilities CAPABILITY_IAM ; aws cloudformation describe-stacks --stack-name ServerlessSpringBootApp");
        //processBuilder.command("/home/ec2-user/migrator/aws/aws-serverless-java-container/samples/springboot/pet-store/deploy.sh");

        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
