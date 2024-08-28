package edu.ucalgary.oop;

import java.util.Objects;

public class FamilyRelation {
    private DisasterVictim personOne;
    private String relationshipTo;
    private DisasterVictim personTwo;

    /**
     * Constructs a new FamilyRelation with the specified persons and relationship.
     *
     * @param personOne the first person in the family relation
     * @param relationshipTo the relationship of the first person to the second person
     * @param personTwo the second person in the family relation
     */
    public FamilyRelation(DisasterVictim personOne, String relationshipTo, DisasterVictim personTwo) {
        this.personOne = personOne;
        this.relationshipTo = relationshipTo;
        this.personTwo = personTwo;
    }

    /**
     * Returns the first person in the family relation.
     *
     * @return the first person in the family relation
     */
    public DisasterVictim getPersonOne() {
        return personOne;
    }

    /**
     * Sets the first person in the family relation.
     *
     * @param personOne the first person in the family relation
     */
    public void setPersonOne(DisasterVictim personOne) {
        this.personOne = personOne;
    }

    /**
     * Returns the relationship of the first person to the second person.
     *
     * @return the relationship of the first person to the second person
     */
    public String getRelationshipTo() {
        return relationshipTo;
    }

    public void setRelationshipTo(String relationshipTo) {
        this.relationshipTo = relationshipTo;
    }

    // Getter and setter for personTwo
    public DisasterVictim getPersonTwo() {
        return personTwo;
    }

    public void setPersonTwo(DisasterVictim personTwo) {
        this.personTwo = personTwo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyRelation that = (FamilyRelation) o;
        return Objects.equals(personOne, that.personOne) &&
                Objects.equals(relationshipTo, that.relationshipTo) &&
                Objects.equals(personTwo, that.personTwo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personOne, relationshipTo, personTwo);
    }
}
