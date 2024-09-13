package com.rafaeltech.musicvault.modules;

public enum ArtistCategory {
    solo("solo"),
    duo("duo"),
    band("band");

    private String category;

    ArtistCategory(String category) {
        this.category = category;
    }

    public static ArtistCategory fromString(String text) {
        for (ArtistCategory artistCategory : ArtistCategory.values()) {
            if (artistCategory.category.equalsIgnoreCase(text)) {
                return artistCategory;
            }
        }
        throw new IllegalArgumentException("no category found for given string: "  + text);
    }
}
