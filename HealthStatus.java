    /**
     * Enum for the health status of a human.
     * HEALTHY       - 
     * CONTAGIOUS    - 
     * ILL           - Infected with symptoms (level given by _symptomsLevel). Initial movement is affected.
     * RECOVERED     - Not infected and sick anymore. Can not be infected again.
     * DEAD          - 
     * @author Franek Stark, Finn Welzmüller
     */
    public enum HealthStatus{

        /**
         * Not infected with COVID-19. No influence on initial movement.
         */
        HEALTY, 

        /**
         * Infected and contagious but no symptoms, hence no influence on initial movement.
         */
        CONTAGIOUS, 

        /**
         * Infected with symptoms (level given by _symptomsLevel). Initial movement is affected.
         */
        ILL, 

        /**
         * Not infected and sick anymore. Can not be infected again.
         */
        RECOVERED, 

        /**
         * person will be deleted.
         */
        DEAD, 
        
        INFECTED};