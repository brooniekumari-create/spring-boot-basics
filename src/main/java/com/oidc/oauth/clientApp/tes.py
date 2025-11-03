import graphviz

uml = graphviz.Digraph(format="png")
uml.attr(rankdir="TB", fontsize="12", nodesep="0.6")

# Abstract Factory Interface (<<interface>>)
uml.node("GUIFactory", '''<<interface>>\nGUIFactory\n+ createButton(): Button\n+ createCheckbox(): Checkbox''', shape="record")

# Concrete Factories
uml.node("WindowsFactory", '''WindowsFactory\n+ createButton(): WindowsButton\n+ createCheckbox(): WindowsCheckbox''', shape="record")
uml.node("MacFactory", '''MacFactory\n+ createButton(): MacButton\n+ createCheckbox(): MacCheckbox''', shape="record")

# Product Interfaces
uml.node("Button", '''<<interface>>\nButton\n+ render()''', shape="record")
uml.node("Checkbox", '''<<interface>>\nCheckbox\n+ render()''', shape="record")

# Concrete Products
uml.node("WindowsButton", "WindowsButton\n+ render()", shape="record")
uml.node("WindowsCheckbox", "WindowsCheckbox\n+ render()", shape="record")
uml.node("MacButton", "MacButton\n+ render()", shape="record")
uml.node("MacCheckbox", "MacCheckbox\n+ render()", shape="record")

# Relationships

# Factory Implementations
uml.edge("GUIFactory", "WindowsFactory", arrowhead="onormal", label="implements")
uml.edge("GUIFactory", "MacFactory", arrowhead="onormal", label="implements")

# Concrete Products Implementing Product Interfaces
uml.edge("Button", "WindowsButton", arrowhead="onormal", label="implements")
uml.edge("Button", "MacButton", arrowhead="onormal", label="implements")
uml.edge("Checkbox", "WindowsCheckbox", arrowhead="onormal", label="implements")
uml.edge("Checkbox", "MacCheckbox", arrowhead="onormal", label="implements")

# Factory "creates" Products (Dashed lines)
uml.edge("WindowsFactory", "WindowsButton", arrowhead="vee", style="dashed", label="creates")
uml.edge("WindowsFactory", "WindowsCheckbox", arrowhead="vee", style="dashed", label="creates")
uml.edge("MacFactory", "MacButton", arrowhead="vee", style="dashed", label="creates")
uml.edge("MacFactory", "MacCheckbox", arrowhead="vee", style="dashed", label="creates")

# Render the updated diagram
uml.render("Updated_AbstractFactory_GUI_UML_Standard", cleanup=True)
