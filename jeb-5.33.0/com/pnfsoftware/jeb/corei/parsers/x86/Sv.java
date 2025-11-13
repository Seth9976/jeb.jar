package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocator;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import java.util.Arrays;
import java.util.List;

public class Sv implements IELFArchRelocatorFacility {
   public static ELFStandardRelocator pC = new K(0);
   public static ELFStandardRelocator A = new HE(1);
   public static ELFStandardRelocator kS = new p(2);
   public static ELFStandardRelocator wS = new GK(3);
   public static ELFStandardRelocator UT = new Mh(4);
   public static ELFStandardRelocator E = new uX(5);
   public static ELFStandardRelocator sY = new Tb(6);
   public static ELFStandardRelocator ys = new ma(7);
   public static ELFStandardRelocator ld = new gJ(8);
   public static ELFStandardRelocator gp = new Ws(9);
   public static ELFStandardRelocator oT = new bO(10);
   public static ELFStandardRelocator fI = new cq(11);
   public static ELFStandardRelocator WR = new DH(12);
   public static ELFStandardRelocator NS = new rQ(13);
   public static ELFStandardRelocator vP = new zp(14);
   public static ELFStandardRelocator xC = new KD(15);
   public static ELFStandardRelocator ED = new yt(24);
   public static ELFStandardRelocator Sc = new RC(37);
   public static ELFRelocationContext ah = new sy(pC, ld, vP, WR, oT, fI, A, kS, wS, UT, gp, NS, xC, ED);
   public static ELFRelocationContext eP = new qt(ld, A, oT, fI, kS, sY, E, WR, NS, vP, xC, ED);
   public static ELFRelocationContext UO = new oP(pC, Sc, sY, ys);
   public static ELFRelocationContext Ab = new vi(A);

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(ah, eP, UO, Ab);
   }
}
