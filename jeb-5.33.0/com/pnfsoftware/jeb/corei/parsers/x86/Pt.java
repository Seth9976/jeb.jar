package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class Pt implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator pC = new lX(0);
   public static ELFStandardRelocator A = new at(1);
   public static ELFStandardRelocator kS = new Mo(2);
   public static ELFStandardRelocator wS = new Nj(3);
   public static ELFStandardRelocator UT = new OW(4);
   public static ELFStandardRelocator E = new aB(5);
   public static ELFStandardRelocator sY = new wq(6);
   public static ELFStandardRelocator ys = new LK(7);
   public static ELFStandardRelocator ld = new Pe(8);
   public static ELFStandardRelocator gp = new pB(9);
   public static ELFStandardRelocator oT = new be(10);
   public static ELFStandardRelocator fI = new hi(20);
   public static ELFStandardRelocator WR = new Rk(21);
   public static ELFStandardRelocator NS = new Qc(22);
   public static ELFStandardRelocator vP = new xt(23);
   public static ELFStandardRelocator xC = new Qn(42);
   public static ELFRelocationContext ED = new NW(pC, A, kS, wS, gp, UT, oT, E, fI, WR, NS, vP);
   public static ELFRelocationContext Sc = new vJ(pC, xC, sY, ys);
   public static ELFRelocationContext ah = new jY(A);
   public static ELFRelocationContext eP = new GA(ld, A, kS, E, fI, WR, NS, vP);
   public static ELFRelocationContext UO = new ao(ld, A, kS, E, sY, fI, WR, NS, vP);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(ED, Sc, ah, eP, UO);
   }
}
