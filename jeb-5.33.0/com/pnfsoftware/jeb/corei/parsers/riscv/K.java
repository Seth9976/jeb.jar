package com.pnfsoftware.jeb.corei.parsers.riscv;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class K implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator pC = new Ws(0);
   public static ELFStandardRelocator A = new bO(1);
   public static ELFStandardRelocator kS = new cq(2);
   public static ELFStandardRelocator wS = new DH(3);
   public static ELFStandardRelocator UT = new rQ(5);
   public static ELFRelocationContext E = new zp(pC, A, kS, wS, UT);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(E);
   }
}
