package com.joust.codalot;

import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;
import com.joust.codalot.service.CodalotGameServiceImpl;
import com.joust.codalot.service.CodalotGameService;
import com.joust.codalot.util.ExitHelper;
import com.joust.codalot.util.ExitHelperImpl;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    ExitHelper exitHelper;

    protected Main(ExitHelper exitHelper) {
        setExitHelper(exitHelper);
    }

    public void run(String[] args) {
        Options options = new Options();
        CommandLineParser parser = new BasicParser();

        options.addOption(OptionBuilder.withLongOpt("knights")
                .withDescription("Knight count")
                .withType(Number.class)
                .hasArg()
                .create("k"));

        options.addOption(OptionBuilder.withLongOpt("days")
                .withDescription("How many days to play the game")
                .withType(Number.class)
                .hasArg()
                .create("d"));

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);


            CodalotGameParameters parameters;
            Integer knightCount = null;
            Integer gameDurationDays = null;

            if (cmd.hasOption("k")) {
                Integer knightCountArg = ((Number) cmd.getParsedOptionValue("k")).intValue();
                if (knightCountArg >= 12) {
                    knightCount = knightCountArg;
                } else {
                    LOG.error("Must have at least 12 knights");
                    exitHelper.exit(1);
                    return;
                }
            }

            if (cmd.hasOption("d")) {
                Integer gameDurationDaysArg = ((Number) cmd.getParsedOptionValue("d")).intValue();
                if (gameDurationDaysArg >= 0) {
                    gameDurationDays = gameDurationDaysArg;
                } else {
                    LOG.error("Must play for at least 1 day");
                    exitHelper.exit(1);
                    return;
                }
            }
            parameters = new CodalotGameParameters.CodalotGameParametersBuilder()
                    .withKnightCount(knightCount)
                    .withGameDurationDays(gameDurationDays)
                    .build();

            LOG.debug("Starting Codalot");
            CodalotGameService gameService = new CodalotGameServiceImpl();
            CodalotGameResult result = gameService.play(parameters);
            LOG.info(result.getMessage());
            LOG.debug("Finished Codalot");
        } catch (ParseException e) {
            LOG.error("Failed to parse comand line properties");
            exitHelper.exit(1);
            return;
        }

        exitHelper.exit(0);

    }

    public static void main(String[] args) {
        Main app = new Main(new ExitHelperImpl());
        app.run(args);
    }

    public ExitHelper getExitHelper() {
        return exitHelper;
    }

    public void setExitHelper(ExitHelper exitHelper) {
        this.exitHelper = exitHelper;
    }
}
