/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.strata.pricer.impl.tree;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.collect.ArgChecker;
import com.opengamma.strata.collect.array.DoubleArray;
import com.opengamma.strata.product.common.PutCall;
import com.opengamma.strata.product.option.BarrierType;

/**
 * Single barrier knock-out option function.
 * <p>
 * The barrier is continuous and the level is constant.
 */
@BeanDefinition(builderScope = "private")
public final class ConstantContinuousSingleBarrierKnockoutFunction
    extends SingleBarrierKnockoutFunction implements ImmutableBean, Serializable {

  /**
   * The strike value.
   */
  @PropertyDefinition(overrideGet = true)
  private final double strike;
  /**
   * The time to expiry.
   */
  @PropertyDefinition(overrideGet = true)
  private final double timeToExpiry;
  /**
   * The sign.
   * <p>
   * The sign is +1 for call and -1 for put.
   */
  @PropertyDefinition(overrideGet = true)
  private final double sign;

  /**
   * The number of time steps.
   */
  @PropertyDefinition(overrideGet = true)
  final int numberOfSteps;

  /**
   * The barrier type.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final BarrierType barrierType;

  /**
   * The constant barrier level.
   */
  @PropertyDefinition
  private final double barrierLevel;

  /**
   * The rebate.
   * <p>
   * The rebate amounts for individual time layer.
   * The size must be equal to {@code numberOfSteps + 1} 
   */
  @PropertyDefinition(validate = "notNull")
  private final DoubleArray rebate;

  //-------------------------------------------------------------------------
  /**
   * Obtains an instance.
   * 
   * @param strike  the strike
   * @param timeToExpiry  the time to expiry
   * @param putCall  put or call
   * @param numberOfSteps  number of steps
   * @param barrierType  the barrier type
   * @param barrierLevel  the barrier level
   * @param rebate  the rebate
   * @return the instance
   */
  public static ConstantContinuousSingleBarrierKnockoutFunction of(
      double strike,
      double timeToExpiry,
      PutCall putCall,
      int numberOfSteps,
      BarrierType barrierType,
      double barrierLevel,
      DoubleArray rebate) {

    ArgChecker.isTrue(numberOfSteps > 0, "the number of steps should be positive");
    ArgChecker.isTrue(numberOfSteps + 1 == rebate.size(), "the size of rebate should be numberOfSteps + 1");
    double sign = putCall.isCall() ? 1d : -1d;
    return new ConstantContinuousSingleBarrierKnockoutFunction(
        strike, timeToExpiry, sign, numberOfSteps, barrierType, barrierLevel, rebate);
  }

  //-------------------------------------------------------------------------
  @Override
  public double getBarrierLevel(int step) {
    return barrierLevel;
  }

  @Override
  public double getRebate(int step) {
    return rebate.get(step);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ConstantContinuousSingleBarrierKnockoutFunction}.
   * @return the meta-bean, not null
   */
  public static ConstantContinuousSingleBarrierKnockoutFunction.Meta meta() {
    return ConstantContinuousSingleBarrierKnockoutFunction.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ConstantContinuousSingleBarrierKnockoutFunction.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private ConstantContinuousSingleBarrierKnockoutFunction(
      double strike,
      double timeToExpiry,
      double sign,
      int numberOfSteps,
      BarrierType barrierType,
      double barrierLevel,
      DoubleArray rebate) {
    JodaBeanUtils.notNull(barrierType, "barrierType");
    JodaBeanUtils.notNull(rebate, "rebate");
    this.strike = strike;
    this.timeToExpiry = timeToExpiry;
    this.sign = sign;
    this.numberOfSteps = numberOfSteps;
    this.barrierType = barrierType;
    this.barrierLevel = barrierLevel;
    this.rebate = rebate;
  }

  @Override
  public ConstantContinuousSingleBarrierKnockoutFunction.Meta metaBean() {
    return ConstantContinuousSingleBarrierKnockoutFunction.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike value.
   * @return the value of the property
   */
  @Override
  public double getStrike() {
    return strike;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the time to expiry.
   * @return the value of the property
   */
  @Override
  public double getTimeToExpiry() {
    return timeToExpiry;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the sign.
   * <p>
   * The sign is +1 for call and -1 for put.
   * @return the value of the property
   */
  @Override
  public double getSign() {
    return sign;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the number of time steps.
   * @return the value of the property
   */
  @Override
  public int getNumberOfSteps() {
    return numberOfSteps;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the barrier type.
   * @return the value of the property, not null
   */
  @Override
  public BarrierType getBarrierType() {
    return barrierType;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the constant barrier level.
   * @return the value of the property
   */
  public double getBarrierLevel() {
    return barrierLevel;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the rebate.
   * <p>
   * The rebate amounts for individual time layer.
   * The size must be equal to {@code numberOfSteps + 1}
   * @return the value of the property, not null
   */
  public DoubleArray getRebate() {
    return rebate;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ConstantContinuousSingleBarrierKnockoutFunction other = (ConstantContinuousSingleBarrierKnockoutFunction) obj;
      return JodaBeanUtils.equal(strike, other.strike) &&
          JodaBeanUtils.equal(timeToExpiry, other.timeToExpiry) &&
          JodaBeanUtils.equal(sign, other.sign) &&
          (numberOfSteps == other.numberOfSteps) &&
          JodaBeanUtils.equal(barrierType, other.barrierType) &&
          JodaBeanUtils.equal(barrierLevel, other.barrierLevel) &&
          JodaBeanUtils.equal(rebate, other.rebate);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(strike);
    hash = hash * 31 + JodaBeanUtils.hashCode(timeToExpiry);
    hash = hash * 31 + JodaBeanUtils.hashCode(sign);
    hash = hash * 31 + JodaBeanUtils.hashCode(numberOfSteps);
    hash = hash * 31 + JodaBeanUtils.hashCode(barrierType);
    hash = hash * 31 + JodaBeanUtils.hashCode(barrierLevel);
    hash = hash * 31 + JodaBeanUtils.hashCode(rebate);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("ConstantContinuousSingleBarrierKnockoutFunction{");
    buf.append("strike").append('=').append(strike).append(',').append(' ');
    buf.append("timeToExpiry").append('=').append(timeToExpiry).append(',').append(' ');
    buf.append("sign").append('=').append(sign).append(',').append(' ');
    buf.append("numberOfSteps").append('=').append(numberOfSteps).append(',').append(' ');
    buf.append("barrierType").append('=').append(barrierType).append(',').append(' ');
    buf.append("barrierLevel").append('=').append(barrierLevel).append(',').append(' ');
    buf.append("rebate").append('=').append(JodaBeanUtils.toString(rebate));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ConstantContinuousSingleBarrierKnockoutFunction}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code strike} property.
     */
    private final MetaProperty<Double> strike = DirectMetaProperty.ofImmutable(
        this, "strike", ConstantContinuousSingleBarrierKnockoutFunction.class, Double.TYPE);
    /**
     * The meta-property for the {@code timeToExpiry} property.
     */
    private final MetaProperty<Double> timeToExpiry = DirectMetaProperty.ofImmutable(
        this, "timeToExpiry", ConstantContinuousSingleBarrierKnockoutFunction.class, Double.TYPE);
    /**
     * The meta-property for the {@code sign} property.
     */
    private final MetaProperty<Double> sign = DirectMetaProperty.ofImmutable(
        this, "sign", ConstantContinuousSingleBarrierKnockoutFunction.class, Double.TYPE);
    /**
     * The meta-property for the {@code numberOfSteps} property.
     */
    private final MetaProperty<Integer> numberOfSteps = DirectMetaProperty.ofImmutable(
        this, "numberOfSteps", ConstantContinuousSingleBarrierKnockoutFunction.class, Integer.TYPE);
    /**
     * The meta-property for the {@code barrierType} property.
     */
    private final MetaProperty<BarrierType> barrierType = DirectMetaProperty.ofImmutable(
        this, "barrierType", ConstantContinuousSingleBarrierKnockoutFunction.class, BarrierType.class);
    /**
     * The meta-property for the {@code barrierLevel} property.
     */
    private final MetaProperty<Double> barrierLevel = DirectMetaProperty.ofImmutable(
        this, "barrierLevel", ConstantContinuousSingleBarrierKnockoutFunction.class, Double.TYPE);
    /**
     * The meta-property for the {@code rebate} property.
     */
    private final MetaProperty<DoubleArray> rebate = DirectMetaProperty.ofImmutable(
        this, "rebate", ConstantContinuousSingleBarrierKnockoutFunction.class, DoubleArray.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "strike",
        "timeToExpiry",
        "sign",
        "numberOfSteps",
        "barrierType",
        "barrierLevel",
        "rebate");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -891985998:  // strike
          return strike;
        case -1831499397:  // timeToExpiry
          return timeToExpiry;
        case 3530173:  // sign
          return sign;
        case -1323103225:  // numberOfSteps
          return numberOfSteps;
        case 1029043089:  // barrierType
          return barrierType;
        case 1827586573:  // barrierLevel
          return barrierLevel;
        case -934952029:  // rebate
          return rebate;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ConstantContinuousSingleBarrierKnockoutFunction> builder() {
      return new ConstantContinuousSingleBarrierKnockoutFunction.Builder();
    }

    @Override
    public Class<? extends ConstantContinuousSingleBarrierKnockoutFunction> beanType() {
      return ConstantContinuousSingleBarrierKnockoutFunction.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code strike} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> strike() {
      return strike;
    }

    /**
     * The meta-property for the {@code timeToExpiry} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> timeToExpiry() {
      return timeToExpiry;
    }

    /**
     * The meta-property for the {@code sign} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> sign() {
      return sign;
    }

    /**
     * The meta-property for the {@code numberOfSteps} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Integer> numberOfSteps() {
      return numberOfSteps;
    }

    /**
     * The meta-property for the {@code barrierType} property.
     * @return the meta-property, not null
     */
    public MetaProperty<BarrierType> barrierType() {
      return barrierType;
    }

    /**
     * The meta-property for the {@code barrierLevel} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> barrierLevel() {
      return barrierLevel;
    }

    /**
     * The meta-property for the {@code rebate} property.
     * @return the meta-property, not null
     */
    public MetaProperty<DoubleArray> rebate() {
      return rebate;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -891985998:  // strike
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getStrike();
        case -1831499397:  // timeToExpiry
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getTimeToExpiry();
        case 3530173:  // sign
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getSign();
        case -1323103225:  // numberOfSteps
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getNumberOfSteps();
        case 1029043089:  // barrierType
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getBarrierType();
        case 1827586573:  // barrierLevel
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getBarrierLevel();
        case -934952029:  // rebate
          return ((ConstantContinuousSingleBarrierKnockoutFunction) bean).getRebate();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code ConstantContinuousSingleBarrierKnockoutFunction}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<ConstantContinuousSingleBarrierKnockoutFunction> {

    private double strike;
    private double timeToExpiry;
    private double sign;
    private int numberOfSteps;
    private BarrierType barrierType;
    private double barrierLevel;
    private DoubleArray rebate;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -891985998:  // strike
          return strike;
        case -1831499397:  // timeToExpiry
          return timeToExpiry;
        case 3530173:  // sign
          return sign;
        case -1323103225:  // numberOfSteps
          return numberOfSteps;
        case 1029043089:  // barrierType
          return barrierType;
        case 1827586573:  // barrierLevel
          return barrierLevel;
        case -934952029:  // rebate
          return rebate;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -891985998:  // strike
          this.strike = (Double) newValue;
          break;
        case -1831499397:  // timeToExpiry
          this.timeToExpiry = (Double) newValue;
          break;
        case 3530173:  // sign
          this.sign = (Double) newValue;
          break;
        case -1323103225:  // numberOfSteps
          this.numberOfSteps = (Integer) newValue;
          break;
        case 1029043089:  // barrierType
          this.barrierType = (BarrierType) newValue;
          break;
        case 1827586573:  // barrierLevel
          this.barrierLevel = (Double) newValue;
          break;
        case -934952029:  // rebate
          this.rebate = (DoubleArray) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public ConstantContinuousSingleBarrierKnockoutFunction build() {
      return new ConstantContinuousSingleBarrierKnockoutFunction(
          strike,
          timeToExpiry,
          sign,
          numberOfSteps,
          barrierType,
          barrierLevel,
          rebate);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(256);
      buf.append("ConstantContinuousSingleBarrierKnockoutFunction.Builder{");
      buf.append("strike").append('=').append(JodaBeanUtils.toString(strike)).append(',').append(' ');
      buf.append("timeToExpiry").append('=').append(JodaBeanUtils.toString(timeToExpiry)).append(',').append(' ');
      buf.append("sign").append('=').append(JodaBeanUtils.toString(sign)).append(',').append(' ');
      buf.append("numberOfSteps").append('=').append(JodaBeanUtils.toString(numberOfSteps)).append(',').append(' ');
      buf.append("barrierType").append('=').append(JodaBeanUtils.toString(barrierType)).append(',').append(' ');
      buf.append("barrierLevel").append('=').append(JodaBeanUtils.toString(barrierLevel)).append(',').append(' ');
      buf.append("rebate").append('=').append(JodaBeanUtils.toString(rebate));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
