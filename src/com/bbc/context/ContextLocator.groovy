package com.bbc.context

class ContextLocator implements Serializable {
    private static IContext _context

    static void registerContext(IContext context) {
        _context = context
    }

    static void registerDefaultContext(Object steps) {
        _context = new DefaultContext(steps)
    }

    static IContext getContext() {
        return _context
    }
}