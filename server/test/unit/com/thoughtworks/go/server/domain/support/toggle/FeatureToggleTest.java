package com.thoughtworks.go.server.domain.support.toggle;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class FeatureToggleTest {
    @Test
    public void shouldKnowWhenItIsOn() throws Exception {
        assertThat(new FeatureToggle("key1", "desc1", true).isOn(), is(true));
    }

    @Test
    public void shouldKnowWhenItIsOff() throws Exception {
        assertThat(new FeatureToggle("key1", "desc1", false).isOn(), is(false));
    }

    @Test
    public void shouldKnowWhenItHasTheSameKeyAsTheProvidedOne() throws Exception {
        assertThat(new FeatureToggle("key1", "desc1", false).hasSameKeyAs("key1"), is(true));
        assertThat(new FeatureToggle("key1", "desc1", false).hasSameKeyAs("KEY1"), is(true));
        assertThat(new FeatureToggle("KEY1", "desc1", false).hasSameKeyAs("key1"), is(true));
    }

    @Test
    public void shouldKnowWhenItDoesNotHaveTheSameKeyAsTheProvidedOne() throws Exception {
        assertThat(new FeatureToggle("key1", "desc1", false).hasSameKeyAs("key2"), is(false));
        assertThat(new FeatureToggle("key1", "desc1", false).hasSameKeyAs("key1_and_suffix"), is(false));
        assertThat(new FeatureToggle("key1", "desc1", false).hasSameKeyAs("prefix_for_key1"), is(false));
    }

    @Test
    public void shouldBeAbleToIndicateThatItsValueHasBeenChanged() throws Exception {
        FeatureToggle existingToggle = new FeatureToggle("key1", "desc1", false);
        FeatureToggle toggleWithValueChangedFlagSet = new FeatureToggle("key1", "desc1", false).withValueHasBeenChangedFlag(true);

        assertThat(existingToggle, is(not(toggleWithValueChangedFlagSet)));
        assertThat(toggleWithValueChangedFlagSet.hasBeenChangedFromDefault(), is(true));
    }

    @Test
    public void shouldBeAbleToCompareItsValueWithThatOfAnotherToggle() throws Exception {
        FeatureToggle toggleWithValueTrue = new FeatureToggle("key1", "desc1", true);
        FeatureToggle toggleWithValueFalse = new FeatureToggle("key2", "desc2", false);

        assertThat(toggleWithValueTrue.hasSameValueAs(toggleWithValueTrue), is(true));
        assertThat(toggleWithValueTrue.hasSameValueAs(toggleWithValueFalse), is(false));
    }
}