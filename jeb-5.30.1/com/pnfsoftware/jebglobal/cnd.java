package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class cnd implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator q = new cne(0);
   public static ELFStandardRelocator RF = new cnf(1);
   public static ELFStandardRelocator xK = new cng(2);
   public static ELFStandardRelocator Dw = new cnh(3);
   public static ELFStandardRelocator Uv = new cni(5);
   public static ELFRelocationContext oW = new cnj(q, RF, xK, Dw, Uv);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(oW);
   }
}
