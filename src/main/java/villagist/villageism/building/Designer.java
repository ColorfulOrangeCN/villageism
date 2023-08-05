package villagist.villageism.building;

import org.jetbrains.annotations.NotNull;

@DesignerType("Abstract")
public interface Designer {
    /**
     * Get the type of designer. (Replace the reflex to get designer information)
     * @return designer type name
     */
    @NotNull default String getName() {
        DesignerType annotation = this.getClass().getAnnotation(DesignerType.class);
        if (annotation == null || annotation.value().equals("")) {
            return getClass().getName();
        } else {
            return annotation.value();
        }
    }

    /**
     *
     * @param style The style to design the building.
     * @param parentDesigner The parent designer, to fetch the related information.
     * @return The result of designing.
     */
    Component[] designFor(Style style, Designer parentDesigner);

    /**
     * Register the designer to use.
     * @param designer The designer to register.
     */
    void registerDesigner(Designer designer);
    Designer[] getDesigners();
}
