package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassSymbol extends Symbol implements Scope {
    protected Scope parent;
    public Map<String, Symbol> attributes = new LinkedHashMap<>();
    public Map<String, FunctionSymbol> functions = new LinkedHashMap<>();
    protected ClassSymbol inherited_class;
    protected TypeSymbol type;


    public ClassSymbol(String name, Scope parent) {
        super(name);
        this.parent = parent;
        this.type = new TypeSymbol(name);
    }

    public void setInherited_class(ClassSymbol inherited_class) {
        this.inherited_class = inherited_class;
    }

    public ClassSymbol getInherited_class() {
        return inherited_class;
    }

    @Override
    public boolean add(Symbol sym) {
        if (sym instanceof FunctionSymbol) {
            if (functions.containsKey(sym.getName())) {
                return false;
            }
            functions.put(sym.getName(), (FunctionSymbol) sym);

        } else {
            if (attributes.containsKey(sym.getName())) {
                return false;
            }
            attributes.put(sym.getName(), sym);
        }
        return true;
    }

    @Override
    public Symbol lookup(String str)  {
        Symbol sym = attributes.get(str);
        if (sym != null) {
            return sym;
        }

        sym = functions.get(str);
        if (sym != null) {
            return sym;
        }

        if (inherited_class != null) {
            sym = inherited_class.lookup(str);
            if (sym != null) {
                return sym;
            }
        }

        if (parent != null) {
            sym = parent.lookup(str);
            return sym;
        }
        return null;
    }

    public TypeSymbol getType() {
        return type;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

}
