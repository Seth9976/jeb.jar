package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import java.util.List;

public interface IClassRebuilder {
   boolean quickDetermination();

   int process();

   List getRebuiltClassItems();

   List getGeneratedMethodReferences();
}
