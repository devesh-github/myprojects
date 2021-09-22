package au.com.anz.robot;

import java.io.IOException;

/**
 * Interface of classes which is able to issue {@link au.com.anz.robot.command.Command} to
 * the robot
 * <p/>
 * User: agwibowo
 */
public interface Commander {

    /**
     * @return the next command that can be executed by the robot
     * @throws IOException
     */
    String getNextCommand() throws IOException;

    /**
     * Clean up the commander, freeing up any resources
     */
    void cleanup();
}
