package org.randito;

@SuppressWarnings("WeakerAccess") // used outside of this project
/*
 * Will inject random values for any fields with the @Rand annotation
 */
public class RanditoAnnotations {
    private RandomizeObjectFields randomizeObjectFields;


    public RanditoAnnotations(Object target) {
        this.randomizeObjectFields = new RandomizeObjectFields(target, true);
    }


    public static void initRands(Object target) {
        new RanditoAnnotations(target).execute();
    }

    private void execute() {
        randomizeObjectFields.execute();
    }
}
