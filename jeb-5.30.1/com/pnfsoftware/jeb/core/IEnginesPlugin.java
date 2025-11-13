package com.pnfsoftware.jeb.core;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface IEnginesPlugin extends IPlugin {
   void load(IEnginesContext var1);

   void execute(IEnginesContext var1);

   void execute(IEnginesContext var1, Map var2);

   default List getExecutionOptionDefinitions(IEnginesContext var1) {
      return this.getExecutionOptionDefinitions();
   }

   default List getExecutionOptionDefinitions() {
      return Collections.emptyList();
   }
}
