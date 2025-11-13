package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class bcf {
   boolean q;
   bbd RF;
   Map xK = new HashMap();
   List Dw = new ArrayList();
   List Uv = new ArrayList();
   List oW = new ArrayList();

   @Override
   public String toString() {
      return Strings.ff(
         "typedef=%b spec=%s basicType=%s basicTypeStack=%s typedefNameStack=%s, declarators(%d):%s",
         this.q,
         this.xK.keySet(),
         this.RF,
         this.Dw,
         this.Uv,
         this.oW.size(),
         this.oW
      );
   }
}
