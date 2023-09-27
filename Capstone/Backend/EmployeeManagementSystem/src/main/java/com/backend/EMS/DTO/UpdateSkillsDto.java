package com.backend.EMS.DTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Data Transfer Object (DTO) for handling skills information.
 */
public class UpdateSkillsDto {
    /**
     * The updated skills for project.
     */
    private List<String> skills;

    @Override
    public final int hashCode() {
        return Objects.hash(skills);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UpdateSkillsDto other = (UpdateSkillsDto) obj;
        return Objects.equals(skills, other.skills);
    }

    @Override
    public final String toString() {
        return "UpdateSkillsDto [skills=" + skills + "]";
    }

    /**
     * @return the skills
     */
    public final List<String> getSkills() {
        if (skills != null) {
        return new ArrayList<>(skills);
        } else {
            return null;
        }
    }

    /**
     * @param skillss the skills to set
     */
    public final void setSkills(final List<String> skillss) {
        if (skillss.size() != 0) {
            this.skills = new ArrayList<>(skillss);
        } else {
            this.skills = null;
        }
    }

}
