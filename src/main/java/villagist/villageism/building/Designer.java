package villagist.villageism.building;

import org.jetbrains.annotations.NotNull;

@DesignerType("Abstract")
public interface Designer {
    /**
     * Get the type of designer. (Replace the reflex to get designer information)
     * @return designer type name
     */
    @NotNull default String getType() {
        DesignerType annotation = this.getClass().getAnnotation(DesignerType.class);
        if (annotation == null) {
            return "";
        } else {
            return annotation.value();
        }
    }

    /**
     *
     * @param style The style to design the building.
     * @param peerDesigners The peer designers, to fetch the related information.
     * @return The result of designing.
     */
    Component[] designFor(Style style, Designer[] peerDesigners);

    /**
     * Register the designer to use.
     * @param designer The designer to register.
     */
    void registerDesigner(Designer designer);
}
