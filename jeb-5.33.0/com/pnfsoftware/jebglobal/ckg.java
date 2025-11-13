package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Ser
public class ckg {
   @SerId(1)
   List pC = new ArrayList();

   public ckg(Collection c) {
      this.pC = new ArrayList(c);
   }

   public List x() {
      return this.pC;
   }
}
