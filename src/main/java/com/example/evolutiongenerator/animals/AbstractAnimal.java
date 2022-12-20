package com.example.evolutiongenerator.animals;

import com.example.evolutiongenerator.Gene;
import com.example.evolutiongenerator.direction.MapDirection;
import com.example.evolutiongenerator.direction.Vector2D;
import com.example.evolutiongenerator.interfaces.IAnimal;
import com.example.evolutiongenerator.interfaces.IMap;
import com.example.evolutiongenerator.interfaces.IMapElementsObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAnimal implements IAnimal {
    protected int actualGenomeIndex;
    protected int energy;
    private int age;
    private int numberOfChildren;
    private int numberOfEatenPlants;
    private int deathDay;
    private final List<IMapElementsObserver> positionObservers = new ArrayList<>();
    protected Vector2D position;
    protected MapDirection direction;
    protected final IMap map;
    protected Gene gene;

    //constructors------------------------------------------
    AbstractAnimal(Vector2D initialPosition, MapDirection initialDirection, IMap map, int genomeLength, int initialEnergy) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.map = map;
        this.gene = new Gene(genomeLength);
        this.actualGenomeIndex = 0;
        this.numberOfEatenPlants = 0;
        this.numberOfChildren = 0;
        this.age = 0;
        this.energy = initialEnergy;
    }

    public AbstractAnimal(Vector2D initialPosition, MapDirection initialDirection, IMap map, Gene gene, int initialEnergy) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.map = map;
        this.gene = gene;
        this.actualGenomeIndex = 0;
        this.numberOfEatenPlants = 0;
        this.numberOfChildren = 0;
        this.age = 0;
        this.energy = initialEnergy;
    }
    //---------------------------------------------------

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public MapDirection getDirection() {
        return direction;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public int getActualGenome() {
        return gene.getGenome()[actualGenomeIndex];
    }

    @Override
    public int[] getGenome() {
        return gene.getGenome();
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    @Override
    public int getDeathDay() {
        return deathDay;
    }

    @Override
    public Gene getGene() {
        return gene;
    }

    @Override
    public int getNumberOfEatenPlants() {
        return numberOfEatenPlants;
    }

    @Override
    public void increaseEnergy(int amount) {
        energy += amount;
    }

    @Override
    public void increaseNumberOfChildren(int quantity) {
        numberOfChildren += quantity;
    }

    @Override
    public void increaseAge(int amount) {
        age += amount;
    }

    @Override
    public void increaseNumberOfEatenPlants(int number) {
        numberOfEatenPlants += number;
    }

    @Override
    public void setDeathDay(int deathDay) {
        this.deathDay = deathDay;
    }

    @Override
    public void addObserver(IMapElementsObserver observer) {
        positionObservers.add(observer);
    }

    @Override
    public void removeObserver(IMapElementsObserver observer) {
        positionObservers.remove(observer);
    }

    protected void informObserversAboutPositionChanges(Vector2D oldPosition, Vector2D newPosition) {
        for (IMapElementsObserver observer : positionObservers) {
            observer.positionChanged(this, oldPosition, newPosition);
        }
    }

}
