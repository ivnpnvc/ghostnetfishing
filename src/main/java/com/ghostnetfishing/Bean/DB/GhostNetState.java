package com.ghostnetfishing.Bean.DB;


public enum GhostNetState {
        REGISTERED("Registriert"),
        SALVAGE_IMMINENT("Für Bergung makiert"),
        SECURE ("Gesichert"),
         MISSING("Verloren");
        private String label;



        private GhostNetState (String label) {
                this.label = label;

        }

        public String getLabel() {
                return label;
        }


}