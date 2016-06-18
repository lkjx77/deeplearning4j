package org.deeplearning4j.spark.api.stats;

import java.io.Serializable;
import java.util.Set;

/**
 * SparkTrainingStats is an interface that is used for accessing training statistics, for multiple {@link org.deeplearning4j.spark.api.TrainingMaster}
 * implementations.
 * <p>
 * The idea is that for debugging purposes, we want to collect a number of statistics related to the training. However, these
 * statistics will vary, depending on which the type of training we are doing. Specifically, both the keys (number of stats)
 * and their actual values (types/classes) can vary.
 * <p>
 * The interface here operates essentially as a {@code Map<String,Object>}. Note however that SparkTrainingStats instances
 * may be nested: for example a {@link org.deeplearning4j.spark.impl.vanilla.stats.VanillaTrainingMasterStats} may have a
 * {@link CommonSparkTrainingStats} instance which may in turn have a {@link org.deeplearning4j.spark.impl.vanilla.stats.VanillaTrainingWorkerStats}
 * instance.
 *
 * @author Alex Black
 */
public interface SparkTrainingStats extends Serializable {

    int PRINT_INDENT = 45;
    String DEFAULT_PRINT_FORMAT = "%-" + PRINT_INDENT + "s";

    Set<String> getKeySet();

    Object getValue(String key);

    /**
     * Combine the two training stats instances. Usually, the two objects must be of the same type
     *
     * @param other Other training stats to return
     */
    void addOtherTrainingStats(SparkTrainingStats other);

    /**
     * Return the nested training stats - if any.
     *
     * @return The nested stats, if present/applicable, or null otherwise
     */
    SparkTrainingStats getNestedTrainingStats();

    /**
     * Get a String representation of the stats. This functionality is implemented as a separate method (as opposed to toString())
     * as the resulting String can be very large.
     *
     * @return A String represetation of the training statistics
     */
    String statsAsString();

}