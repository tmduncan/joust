package com.joust.codalot;

import com.joust.codalot.domain.game.CodalotGameParameters;
import com.joust.codalot.domain.game.CodalotGameResult;
import com.joust.codalot.service.CodalotGameServiceImpl;
import com.joust.codalot.service.CodalotGameService;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private Main() {
    }

    public static int main(String[] args) {

        Options options = new Options();
        CommandLineParser parser = new BasicParser();

        options.addOption(OptionBuilder.withLongOpt("knights")
                .withDescription("Knight count")
                .withType(Number.class)
                .hasArg()
                .create("k"));

        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);


            CodalotGameParameters parameters = new CodalotGameParameters();
            if (cmd.hasOption("k")) {
                Integer knightCount = ((Number) cmd.getParsedOptionValue("k")).intValue();
                if (knightCount >= 12){
                    parameters = new CodalotGameParameters.CodalotGameParametersBuilder().withKnightCount(knightCount).build();
                } else{
                    LOG.error("Must have at least 12 knights");
                    return 1;
                }
            }

            LOG.debug("Starting Codalot");
            CodalotGameService gameService = new CodalotGameServiceImpl();
            CodalotGameResult result = gameService.play(parameters);
            LOG.info(result.getMessage());
            LOG.debug("Finished Codalot");
        } catch (ParseException e) {
            LOG.error("Failed to parse comand line properties");
            return 1;
        }

        return 0;

    }


}
