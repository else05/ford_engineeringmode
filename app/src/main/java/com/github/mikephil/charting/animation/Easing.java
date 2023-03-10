package com.github.mikephil.charting.animation;

/* loaded from: classes2.dex */
public class Easing {

    /* loaded from: classes2.dex */
    public enum EasingOption {
        Linear,
        EaseInQuad,
        EaseOutQuad,
        EaseInOutQuad,
        EaseInCubic,
        EaseOutCubic,
        EaseInOutCubic,
        EaseInQuart,
        EaseOutQuart,
        EaseInOutQuart,
        EaseInSine,
        EaseOutSine,
        EaseInOutSine,
        EaseInExpo,
        EaseOutExpo,
        EaseInOutExpo,
        EaseInCirc,
        EaseOutCirc,
        EaseInOutCirc,
        EaseInElastic,
        EaseOutElastic,
        EaseInOutElastic,
        EaseInBack,
        EaseOutBack,
        EaseInOutBack,
        EaseInBounce,
        EaseOutBounce,
        EaseInOutBounce
    }

    public static EasingFunction getEasingFunctionFromOption(EasingOption easing) {
        switch (easing) {
            case EaseInQuad:
                return EasingFunctions.EaseInQuad;
            case EaseOutQuad:
                return EasingFunctions.EaseOutQuad;
            case EaseInOutQuad:
                return EasingFunctions.EaseInOutQuad;
            case EaseInCubic:
                return EasingFunctions.EaseInCubic;
            case EaseOutCubic:
                return EasingFunctions.EaseOutCubic;
            case EaseInOutCubic:
                return EasingFunctions.EaseInOutCubic;
            case EaseInQuart:
                return EasingFunctions.EaseInQuart;
            case EaseOutQuart:
                return EasingFunctions.EaseOutQuart;
            case EaseInOutQuart:
                return EasingFunctions.EaseInOutQuart;
            case EaseInSine:
                return EasingFunctions.EaseInSine;
            case EaseOutSine:
                return EasingFunctions.EaseOutSine;
            case EaseInOutSine:
                return EasingFunctions.EaseInOutSine;
            case EaseInExpo:
                return EasingFunctions.EaseInExpo;
            case EaseOutExpo:
                return EasingFunctions.EaseOutExpo;
            case EaseInOutExpo:
                return EasingFunctions.EaseInOutExpo;
            case EaseInCirc:
                return EasingFunctions.EaseInCirc;
            case EaseOutCirc:
                return EasingFunctions.EaseOutCirc;
            case EaseInOutCirc:
                return EasingFunctions.EaseInOutCirc;
            case EaseInElastic:
                return EasingFunctions.EaseInElastic;
            case EaseOutElastic:
                return EasingFunctions.EaseOutElastic;
            case EaseInOutElastic:
                return EasingFunctions.EaseInOutElastic;
            case EaseInBack:
                return EasingFunctions.EaseInBack;
            case EaseOutBack:
                return EasingFunctions.EaseOutBack;
            case EaseInOutBack:
                return EasingFunctions.EaseInOutBack;
            case EaseInBounce:
                return EasingFunctions.EaseInBounce;
            case EaseOutBounce:
                return EasingFunctions.EaseOutBounce;
            case EaseInOutBounce:
                return EasingFunctions.EaseInOutBounce;
            default:
                return EasingFunctions.Linear;
        }
    }

    /* loaded from: classes2.dex */
    private static class EasingFunctions {
        public static final EasingFunction Linear = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.1
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input;
            }
        };
        public static final EasingFunction EaseInQuad = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.2
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input * input;
            }
        };
        public static final EasingFunction EaseOutQuad = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.3
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return (-input) * (input - 2.0f);
            }
        };
        public static final EasingFunction EaseInOutQuad = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.4
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position >= 1.0f) {
                    float position2 = position - 1.0f;
                    return ((position2 * (position2 - 2.0f)) - 1.0f) * (-0.5f);
                }
                return 0.5f * position * position;
            }
        };
        public static final EasingFunction EaseInCubic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.5
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input * input * input;
            }
        };
        public static final EasingFunction EaseOutCubic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.6
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float input2 = input - 1.0f;
                return (input2 * input2 * input2) + 1.0f;
            }
        };
        public static final EasingFunction EaseInOutCubic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.7
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < 1.0f) {
                    return 0.5f * position * position * position;
                }
                float position2 = position - 2.0f;
                return ((position2 * position2 * position2) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInQuart = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.8
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input * input * input * input;
            }
        };
        public static final EasingFunction EaseOutQuart = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.9
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float input2 = input - 1.0f;
                return -((((input2 * input2) * input2) * input2) - 1.0f);
            }
        };
        public static final EasingFunction EaseInOutQuart = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.10
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < 1.0f) {
                    return 0.5f * position * position * position * position;
                }
                float position2 = position - 2.0f;
                return ((((position2 * position2) * position2) * position2) - 2.0f) * (-0.5f);
            }
        };
        public static final EasingFunction EaseInSine = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.11
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return (-((float) Math.cos(input * 1.5707963267948966d))) + 1.0f;
            }
        };
        public static final EasingFunction EaseOutSine = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.12
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return (float) Math.sin(input * 1.5707963267948966d);
            }
        };
        public static final EasingFunction EaseInOutSine = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.13
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return (((float) Math.cos(input * 3.141592653589793d)) - 1.0f) * (-0.5f);
            }
        };
        public static final EasingFunction EaseInExpo = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.14
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                return (float) Math.pow(2.0d, (input - 1.0f) * 10.0f);
            }
        };
        public static final EasingFunction EaseOutExpo = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.15
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 1.0f) {
                    return 1.0f;
                }
                return -((float) Math.pow(2.0d, (1.0f + input) * (-10.0f)));
            }
        };
        public static final EasingFunction EaseInOutExpo = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.16
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                if (input == 1.0f) {
                    return 1.0f;
                }
                float position = input / 0.5f;
                return position < 1.0f ? ((float) Math.pow(2.0d, (position - 1.0f) * 10.0f)) * 0.5f : ((-((float) Math.pow(2.0d, (position - 1.0f) * (-10.0f)))) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInCirc = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.17
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return -(((float) Math.sqrt(1.0f - (input * input))) - 1.0f);
            }
        };
        public static final EasingFunction EaseOutCirc = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.18
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float input2 = input - 1.0f;
                return (float) Math.sqrt(1.0f - (input2 * input2));
            }
        };
        public static final EasingFunction EaseInOutCirc = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.19
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < 1.0f) {
                    return (((float) Math.sqrt(1.0f - (position * position))) - 1.0f) * (-0.5f);
                }
                float position2 = position - 2.0f;
                return (((float) Math.sqrt(1.0f - (position2 * position2))) + 1.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInElastic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.20
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                if (input == 1.0f) {
                    return 1.0f;
                }
                float s = (0.3f / 6.2831855f) * ((float) Math.asin(1.0d));
                float position = input - 1.0f;
                return -(((float) Math.pow(2.0d, position * 10.0f)) * ((float) Math.sin(((position - s) * 6.283185307179586d) / 0.3f)));
            }
        };
        public static final EasingFunction EaseOutElastic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.21
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                if (input == 1.0f) {
                    return 1.0f;
                }
                float s = (0.3f / 6.2831855f) * ((float) Math.asin(1.0d));
                return (((float) Math.pow(2.0d, (-10.0f) * input)) * ((float) Math.sin(((input - s) * 6.283185307179586d) / 0.3f))) + 1.0f;
            }
        };
        public static final EasingFunction EaseInOutElastic = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.22
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input == 0.0f) {
                    return 0.0f;
                }
                float position = input / 0.5f;
                if (position == 2.0f) {
                    return 1.0f;
                }
                float s = (0.45000002f / 6.2831855f) * ((float) Math.asin(1.0d));
                if (position >= 1.0f) {
                    float position2 = position - 1.0f;
                    return (((float) Math.pow(2.0d, position2 * (-10.0f))) * ((float) Math.sin((((position2 * 1.0f) - s) * 6.283185307179586d) / 0.45000002f)) * 0.5f) + 1.0f;
                }
                float position3 = position - 1.0f;
                return ((float) Math.pow(2.0d, position3 * 10.0f)) * ((float) Math.sin((((1.0f * position3) - s) * 6.283185307179586d) / 0.45000002f)) * (-0.5f);
            }
        };
        public static final EasingFunction EaseInBack = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.23
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input * input * ((2.70158f * input) - 1.70158f);
            }
        };
        public static final EasingFunction EaseOutBack = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.24
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input - 1.0f;
                return (position * position * ((2.70158f * position) + 1.70158f)) + 1.0f;
            }
        };
        public static final EasingFunction EaseInOutBack = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.25
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                float position = input / 0.5f;
                if (position < 1.0f) {
                    float s = 1.525f * 1.70158f;
                    return position * position * (((s + 1.0f) * position) - s) * 0.5f;
                }
                float position2 = position - 2.0f;
                float s2 = 1.525f * 1.70158f;
                return ((position2 * position2 * (((s2 + 1.0f) * position2) + s2)) + 2.0f) * 0.5f;
            }
        };
        public static final EasingFunction EaseInBounce = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.26
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return 1.0f - EasingFunctions.EaseOutBounce.getInterpolation(1.0f - input);
            }
        };
        public static final EasingFunction EaseOutBounce = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.27
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                if (input < 0.36363637f) {
                    return 7.5625f * input * input;
                }
                if (input < 0.72727275f) {
                    float position = input - 0.54545456f;
                    return (position * 7.5625f * position) + 0.75f;
                } else if (input < 0.90909094f) {
                    float position2 = input - 0.8181818f;
                    return (position2 * 7.5625f * position2) + 0.9375f;
                } else {
                    float position3 = input - 0.95454544f;
                    return (position3 * 7.5625f * position3) + 0.984375f;
                }
            }
        };
        public static final EasingFunction EaseInOutBounce = new EasingFunction() { // from class: com.github.mikephil.charting.animation.Easing.EasingFunctions.28
            @Override // com.github.mikephil.charting.animation.EasingFunction, android.animation.TimeInterpolator
            public float getInterpolation(float input) {
                return input < 0.5f ? EasingFunctions.EaseInBounce.getInterpolation(2.0f * input) * 0.5f : (EasingFunctions.EaseOutBounce.getInterpolation((2.0f * input) - 1.0f) * 0.5f) + 0.5f;
            }
        };

        private EasingFunctions() {
        }
    }
}
