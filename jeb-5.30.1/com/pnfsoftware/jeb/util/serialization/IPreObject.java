package com.pnfsoftware.jeb.util.serialization;

import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import java.util.Map;

interface IPreObject {
   boolean canBuild(Map var1, IdentityHashSet var2);

   Object build(Map var1);

   Object getObject();
}
