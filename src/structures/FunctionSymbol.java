package cool.structures;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends IdSymbol implements Scope {
    protected Scope parent;
    protected Map<String, Symbol> formals = new LinkedHashMap<>();
    protected ClassSymbol owned_class;

    public FunctionSymbol(Scope parent, String name, ClassSymbol owned_class) {
        super(name);
        this.parent = parent;
        this.owned_class = owned_class;
    }

    @Override
    public boolean add(Symbol sym) {
        if (formals.containsKey(sym.getName())) {
            return false;
        }

        formals.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String s) {
        var sym = formals.get(s);

        if (sym != null) {
            return sym;
        }

        if (parent != null) {
            return parent.lookup(s);
        }

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public Map<String, Symbol> getFormals() {
        return formals;
    }

    public ClassSymbol getOwnedClass() {
        return owned_class;
    }

}