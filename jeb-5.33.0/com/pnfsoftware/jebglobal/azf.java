package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class azf {
   boolean pC;
   aye A;
   Map kS = new HashMap();
   List wS = new ArrayList();
   List UT = new ArrayList();
   List E = new ArrayList();

   @Override
   public String toString() {
      return Strings.ff(
         "typedef=%b spec=%s basicType=%s basicTypeStack=%s typedefNameStack=%s, declarators(%d):%s",
         this.pC,
         this.kS.keySet(),
         this.A,
         this.wS,
         this.UT,
         this.E.size(),
         this.E
      );
   }
}
