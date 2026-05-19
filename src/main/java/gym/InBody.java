package gym;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.util.Objects;

public class InBody {
    private LocalDate date;
    private float height;
    private float weight;
    private float bodyFatMass;
    private float minerals;
    private float totalBodyWater;
    private float protein;

    public InBody() {
    }

    @JsonCreator
    public InBody(@JsonProperty("date") LocalDate date,
                  @JsonProperty("height") float height,
                  @JsonProperty("weight") float weight,
                  @JsonProperty("bodyFatMass") float bodyFatMass,
                  @JsonProperty("minerals") float minerals,
                  @JsonProperty("totalBodyWater") float totalBodyWater,
                  @JsonProperty("protein") float protein) {
        this.date = date;
        setHeight(height);
        setWeight(weight);
        setBodyFatMass(bodyFatMass);
        setMinerals(minerals);
        setTotalBodyWater(totalBodyWater);
        setProtein(protein);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        if (height < 0) {
            throw new IllegalArgumentException("Height cannot be negative");
        }
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weight = weight;
    }

    public float getBodyFatMass() {
        return bodyFatMass;
    }

    public void setBodyFatMass(float bodyFatMass) {
        if (bodyFatMass < 0) {
            throw new IllegalArgumentException("Body fat mass cannot be negative");
        }
        this.bodyFatMass = bodyFatMass;
    }

    public float getMinerals() {
        return minerals;
    }

    public void setMinerals(float minerals) {
        if (minerals < 0) {
            throw new IllegalArgumentException("Minerals cannot be negative");
        }
        this.minerals = minerals;
    }

    public float getTotalBodyWater() {
        return totalBodyWater;
    }

    public void setTotalBodyWater(float totalBodyWater) {
        if (totalBodyWater < 0) {
            throw new IllegalArgumentException("Total body water cannot be negative");
        }
        this.totalBodyWater = totalBodyWater;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        if (protein < 0) {
            throw new IllegalArgumentException("Protein cannot be negative");
        }
        this.protein = protein;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InBody)) {
            return false;
        }
        InBody inBody = (InBody) o;
        return Float.compare(inBody.height, height) == 0
                && Float.compare(inBody.weight, weight) == 0
                && Float.compare(inBody.bodyFatMass, bodyFatMass) == 0
                && Float.compare(inBody.minerals, minerals) == 0
                && Float.compare(inBody.totalBodyWater, totalBodyWater) == 0
                && Float.compare(inBody.protein, protein) == 0
                && Objects.equals(date, inBody.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, height, weight, bodyFatMass,
                minerals, totalBodyWater, protein);
    }

    @Override
    public String toString() {
        return "InBody{" +
                "date=" + date +
                ", height=" + height + " m" +
                ", weight=" + weight + " kg" +
                ", bodyFatMass=" + bodyFatMass + " kg" +
                ", minerals=" + minerals + " kg" +
                ", totalBodyWater=" + totalBodyWater + " kg" +
                ", protein=" + protein + " kg" +
                '}';
    }
}