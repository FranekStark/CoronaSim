    /**
     * HEALTHY       - Not infected with COVID-19. No influence on initial movement.
     * CONTAGIOUS    - Infected and contagious but no symptoms, hence no influence on initial movement.
     * ILL           - Infected with symptoms (level given by _symptomsLevel). Initial movement is affected.
     * RECOVERED     - Not infected and sick anymore. Can not be infected again.
     * DEAD          - person will be deleted.
     */
    public enum HealthStatus{HEALTY, CONTAGIOUS, ILL, RECOVERED, DEAD};