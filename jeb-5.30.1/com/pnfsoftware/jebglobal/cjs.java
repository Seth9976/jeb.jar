package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvalCodeThrownException;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.EEmulator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.IEEmulatorHooks;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator.RoutineIOHandler;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VMReader;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class cjs implements IEEmulatorHooks {
   static final int q = 2;
   static final int RF = 1;
   static final int xK = -2;
   static final int Dw = -1;
   static final int Uv = -3;
   static final int oW = 0;
   static final int gO = 0;
   static final int nf = 1;
   static final int gP = 65537;
   static final int za = 65538;
   static final int lm = 65540;
   static final int zz = 65542;
   private static final int Me = 0;
   private static final int PV = 1;
   private static final int oQ = 2;
   private static final int xW = 3;
   private static final int KT = 65537;
   private static final int Gf = 65542;
   cjo JY;
   EEmulator HF;
   IVirtualMemory LK;
   IEGlobalContext io;
   ProcessorType qa;
   boolean Hk;
   private RoutineIOHandler Ef;

   static void q(String var0, Object... var1) {
   }

   static void RF(String var0, Object... var1) {
   }

   cjs(cjo var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.JY = var1;
         this.HF = var1.za();
         this.LK = this.HF.getVirtualMemory();
         this.io = this.HF.getGlobalContext();
         this.qa = this.io.getNativeContext().getProcessor().getType();
         this.Hk = var1.oW.getGPRegisterBitsize() == 64;
         this.Ef = new RoutineIOHandler(this.HF);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      switch (cvm.xK(var2)) {
         case -2146792506:
         case -2134950756:
         case -2120936779:
         case -2097579900:
         case -2051890678:
         case -2051688172:
         case -2016526083:
         case -2012118445:
         case -1982715289:
         case -1948659323:
         case -1948333157:
         case -1943483988:
         case -1943211781:
         case -1931363395:
         case -1924218098:
         case -1916688867:
         case -1914909024:
         case -1878685356:
         case -1838906575:
         case -1826521030:
         case -1819897800:
         case -1808452158:
         case -1799386836:
         case -1789605352:
         case -1785426108:
         case -1784231296:
         case -1781666358:
         case -1762186721:
         case -1733680079:
         case -1714194354:
         case -1688064942:
         case -1678869756:
         case -1674784009:
         case -1649412263:
         case -1615592845:
         case -1591999000:
         case -1577428797:
         case -1569093027:
         case -1498942684:
         case -1492562733:
         case -1490535854:
         case -1462753928:
         case -1439307331:
         case -1414923680:
         case -1394772922:
         case -1364750509:
         case -1336500526:
         case -1328586975:
         case -1306977625:
         case -1255184679:
         case -1246288620:
         case -1245617332:
         case -1236449472:
         case -1223888971:
         case -1223314468:
         case -1222903032:
         case -1204806184:
         case -1200119012:
         case -1185334531:
         case -1135228531:
         case -1130056856:
         case -1095953659:
         case -1095472940:
         case -1090370219:
         case -1084054661:
         case -1051831395:
         case -1022466774:
         case -968098752:
         case -965785503:
         case -945387788:
         case -932617484:
         case -921253872:
         case -911549441:
         case -888122860:
         case -873740904:
         case -867914684:
         case -864523024:
         case -852353139:
         case -837251673:
         case -833102966:
         case -832586445:
         case -809066121:
         case -779904145:
         case -769792372:
         case -750297513:
         case -727180593:
         case -715027112:
         case -711410735:
         case -708564284:
         case -707370984:
         case -702155279:
         case -701651984:
         case -699995339:
         case -677503563:
         case -661477963:
         case -657315539:
         case -652845793:
         case -623458268:
         case -604053979:
         case -593476110:
         case -581214837:
         case -579618570:
         case -565300011:
         case -555619603:
         case -512522823:
         case -493824257:
         case -490302269:
         case -466470545:
         case -428425146:
         case -415116730:
         case -408797597:
         case -395427250:
         case -390949721:
         case -388730666:
         case -386390433:
         case -385424675:
         case -335759550:
         case -318852672:
         case -252051364:
         case -246422608:
         case -206865257:
         case -199909539:
         case -199578785:
         case -144535406:
         case -112413852:
         case -79866638:
         case -34985879:
         case -29874824:
         case -16075565:
         case -4669976:
         case 31040006:
         case 34719615:
         case 42261176:
         case 92008111:
         case 99207718:
         case 128747975:
         case 135434879:
         case 140170388:
         case 144110970:
         case 148708192:
         case 159656454:
         case 167236625:
         case 171085380:
         case 186191386:
         case 193096395:
         case 200650863:
         case 250945779:
         case 261910796:
         case 269185143:
         case 295494544:
         case 336264978:
         case 364608423:
         case 388176200:
         case 389891295:
         case 401317503:
         case 411443080:
         case 421357621:
         case 431251782:
         case 476786442:
         case 552183643:
         case 561293667:
         case 577170125:
         case 633268445:
         case 677031288:
         case 712897499:
         case 722129746:
         case 737063522:
         case 740969735:
         case 761982294:
         case 810002497:
         case 816092528:
         case 828670056:
         case 846090276:
         case 847952879:
         case 856237346:
         case 878749552:
         case 892394213:
         case 979980119:
         case 985139829:
         case 1004043698:
         case 1018439764:
         case 1021601417:
         case 1029630465:
         case 1105816151:
         case 1116709725:
         case 1125005996:
         case 1158014871:
         case 1172415263:
         case 1206140885:
         case 1256698242:
         case 1311320894:
         case 1360784728:
         case 1376556447:
         case 1432154034:
         case 1465427985:
         case 1474401868:
         case 1477023054:
         case 1488856767:
         case 1495610894:
         case 1497714135:
         case 1498491051:
         case 1564462299:
         case 1567541837:
         case 1586414828:
         case 1600322159:
         case 1619804715:
         case 1648243687:
         case 1650328425:
         case 1667025175:
         case 1681513940:
         case 1684260316:
         case 1697043521:
         case 1713573578:
         case 1739278613:
         case 1774565545:
         case 1782232408:
         case 1793833288:
         case 1799892967:
         case 1829605578:
         case 1836847084:
         case 1856424862:
         case 1869495233:
         case 1886159338:
         case 1887682946:
         case 1911845198:
         case 1950595473:
         case 1976222283:
         case 1994000148:
         case 1994699200:
         case 2022152394:
         case 2027859044:
         case 2108697204:
         case 2114197384:
         case 2141110471:
            this.q(var2);
            return true;
         default:
            return null;
      }
   }

   void q(String var1) throws cju {
      this.Ef.reset(this.JY.Uv);
      if (this.JY.sH != 0) {
         switch (cvm.xK(var1)) {
            case -1943483988:
            case -1781666358:
            case -1714194354:
            case -1591999000:
            case -1490535854:
            case -1022466774:
            case -968098752:
            case -921253872:
            case -873740904:
            case -832586445:
            case -711410735:
            case -708564284:
            case -335759550:
            case -206865257:
            case 140170388:
            case 193096395:
            case 846090276:
            case 1158014871:
            case 1360784728:
            case 1911845198:
            case 2022152394:
            case 2114197384:
               break;
            default:
               throw new cju(
                  cvm.q(
                        new byte[]{87, 59, 6, 9, 15, 10, 8, 9, 1, 68, 69, 29, 27, 6, 21, 4, 29, 6, 1, 78, 73, 7, 78, 106, 4, 7, 101, 12, 82, 23, 3, 15, 13, 89},
                        1,
                        2
                     )
                     + this.JY.sH
               );
         }
      }

      switch (cvm.xK(var1)) {
         case -2146792506:
            this.pL();
            break;
         case -2134950756:
            this.eC();
            break;
         case -2120936779:
            this.fw();
            break;
         case -2097579900:
            this.oS();
            break;
         case -2051890678:
            this.Jf();
            break;
         case -2051688172:
            this.Xz();
            break;
         case -2016526083:
            this.Oj();
            break;
         case -2012118445:
            this.lF();
            break;
         case -1982715289:
            this.cO();
            break;
         case -1948659323:
            this.kf();
            break;
         case -1948333157:
            this.Wp();
            break;
         case -1943483988:
            this.XV();
            break;
         case -1943211781:
            this.Qe();
            break;
         case -1931363395:
            this.tb();
            break;
         case -1924218098:
            this.dW();
            break;
         case -1916688867:
            this.xf();
            break;
         case -1914909024:
            this.YA();
            break;
         case -1878685356:
            this.CK();
            break;
         case -1838906575:
            this.PQ();
            break;
         case -1826521030:
            this.CE();
            break;
         case -1819897800:
            this.mJ();
            break;
         case -1808452158:
            this.bY();
            break;
         case -1799386836:
            this.eb();
            break;
         case -1789605352:
            this.zx();
            break;
         case -1785426108:
            this.CW();
            break;
         case -1784231296:
            this.Qo();
            break;
         case -1781666358:
            this.lz();
            break;
         case -1762186721:
            this.YN();
            break;
         case -1733680079:
            this.Uv();
            break;
         case -1714194354:
            this.Gf();
            break;
         case -1688064942:
            this.Rv();
            break;
         case -1678869756:
            this.Ag();
            break;
         case -1674784009:
            this.zz();
            break;
         case -1649412263:
            this.Nu();
            break;
         case -1615592845:
            this.Lj();
            break;
         case -1591999000:
            this.Me();
            break;
         case -1577428797:
            this.RF();
            break;
         case -1569093027:
            this.kv();
            break;
         case -1498942684:
            this.Yw();
            break;
         case -1492562733:
            this.kk();
            break;
         case -1490535854:
            this.Hk();
            break;
         case -1462753928:
            this.iK();
            break;
         case -1439307331:
            this.fG();
            break;
         case -1414923680:
            this.ui();
            break;
         case -1394772922:
            this.LR();
            break;
         case -1364750509:
            this.dS();
            break;
         case -1336500526:
            this.NY();
            break;
         case -1328586975:
            this.uw();
            break;
         case -1306977625:
            this.uY();
            break;
         case -1255184679:
            this.jk();
            break;
         case -1246288620:
            this.Wx();
            break;
         case -1245617332:
            this.zm();
            break;
         case -1236449472:
            this.ZE();
            break;
         case -1223888971:
            this.lA();
            break;
         case -1223314468:
            this.xG();
            break;
         case -1222903032:
            this.jT();
            break;
         case -1204806184:
            this.Jh();
            break;
         case -1200119012:
            this.gm();
            break;
         case -1185334531:
            this.Kl();
            break;
         case -1135228531:
            this.qR();
            break;
         case -1130056856:
            this.WJ();
            break;
         case -1095953659:
            this.sc();
            break;
         case -1095472940:
            this.HF();
            break;
         case -1090370219:
            this.Gu();
            break;
         case -1084054661:
            this.pe();
            break;
         case -1051831395:
            this.yn();
            break;
         case -1022466774:
            this.GO();
            break;
         case -968098752:
            this.bj();
            break;
         case -965785503:
            this.mI();
            break;
         case -945387788:
            this.Xo();
            break;
         case -932617484:
            this.nq();
            break;
         case -921253872:
            this.Ef();
            break;
         case -911549441:
            this.BY();
            break;
         case -888122860:
            this.HK();
            break;
         case -873740904:
            this.Up();
            break;
         case -867914684:
            this.iO();
            break;
         case -864523024:
            this.MT();
            break;
         case -852353139:
            this.JY();
            break;
         case -837251673:
            this.fe();
            break;
         case -833102966:
            this.hy();
            break;
         case -832586445:
            this.sa();
            break;
         case -809066121:
            this.CB();
            break;
         case -779904145:
            this.Ov();
            break;
         case -769792372:
            this.LK();
            break;
         case -750297513:
            this.Oz();
            break;
         case -727180593:
            this.JF();
            break;
         case -715027112:
            this.VW();
            break;
         case -711410735:
            this.lk();
            break;
         case -708564284:
            this.fK();
            break;
         case -707370984:
            this.cy();
            break;
         case -702155279:
            this.LL();
            break;
         case -701651984:
            this.Dz();
            break;
         case -699995339:
            this.KT();
            break;
         case -677503563:
            this.Rr();
            break;
         case -661477963:
            this.PW();
            break;
         case -657315539:
            this.lN();
            break;
         case -652845793:
            this.GM();
            break;
         case -623458268:
            this.wN();
            break;
         case -604053979:
            this.fQ();
            break;
         case -593476110:
            this.PV();
            break;
         case -581214837:
            this.dF();
            break;
         case -579618570:
            this.nf();
            break;
         case -565300011:
            this.AN();
            break;
         case -555619603:
            this.AB();
            break;
         case -512522823:
            this.TQ();
            break;
         case -493824257:
            this.Al();
            break;
         case -490302269:
            this.fN();
            break;
         case -466470545:
            this.GC();
            break;
         case -428425146:
            this.RL();
            break;
         case -415116730:
            this.jq();
            break;
         case -408797597:
            this.aV();
            break;
         case -395427250:
            this.Tq();
            break;
         case -390949721:
            this.Uz();
            break;
         case -388730666:
            this.Wn();
            break;
         case -386390433:
            this.ZT();
            break;
         case -385424675:
            this.br();
            break;
         case -335759550:
            this.oQ();
            break;
         case -318852672:
            this.Kn();
            break;
         case -252051364:
            this.fq();
            break;
         case -246422608:
            this.os();
            break;
         case -206865257:
            this.YT();
            break;
         case -199909539:
            this.GH();
            break;
         case -199578785:
            this.cR();
            break;
         case -144535406:
            this.eG();
            break;
         case -112413852:
            this.BU();
            break;
         case -79866638:
            this.rp();
            break;
         case -34985879:
            this.rk();
            break;
         case -29874824:
            this.AG();
            break;
         case -16075565:
            this.Id();
            break;
         case -4669976:
            this.xK();
            break;
         case 31040006:
            this.rL();
            break;
         case 34719615:
            this.eJ();
            break;
         case 42261176:
            this.TB();
            break;
         case 92008111:
            this.GY();
            break;
         case 99207718:
            this.So();
            break;
         case 128747975:
            this.hP();
            break;
         case 135434879:
            this.uz();
            break;
         case 140170388:
            this.xW();
            break;
         case 144110970:
            this.io();
            break;
         case 148708192:
            this.RW();
            break;
         case 159656454:
            this.nv();
            break;
         case 167236625:
            this.WX();
            break;
         case 171085380:
            this.hM();
            break;
         case 186191386:
            this.NX();
            break;
         case 193096395:
            this.HO();
            break;
         case 200650863:
            this.er();
            break;
         case 250945779:
            this.ap();
            break;
         case 261910796:
            this.gl();
            break;
         case 269185143:
            this.qr();
            break;
         case 295494544:
            this.gO();
            break;
         case 336264978:
            this.DP();
            break;
         case 364608423:
            this.jb();
            break;
         case 388176200:
            this.rV();
            break;
         case 389891295:
            this.sH();
            break;
         case 401317503:
            this.cC();
            break;
         case 411443080:
            this.Ub();
            break;
         case 421357621:
            this.cb();
            break;
         case 431251782:
            this.oW();
            break;
         case 476786442:
            this.Rc();
            break;
         case 552183643:
            this.dg();
            break;
         case 561293667:
            this.wF();
            break;
         case 577170125:
            this.Qu();
            break;
         case 633268445:
            this.Eq();
            break;
         case 677031288:
            this.CY();
            break;
         case 712897499:
            this.bl();
            break;
         case 722129746:
            this.yW();
            break;
         case 737063522:
            this.q();
            break;
         case 740969735:
            this.fn();
            break;
         case 761982294:
            this.IN();
            break;
         case 810002497:
            this.zj();
            break;
         case 816092528:
            this.Xi();
            break;
         case 828670056:
            this.Gg();
            break;
         case 846090276:
            this.o();
            break;
         case 847952879:
            this.of();
            break;
         case 856237346:
            this.gT();
            break;
         case 878749552:
            this.Dw();
            break;
         case 892394213:
            this.WI();
            break;
         case 979980119:
            this.hw();
            break;
         case 985139829:
            this.gP();
            break;
         case 1004043698:
            this.EB();
            break;
         case 1018439764:
            this.Bs();
            break;
         case 1021601417:
            this.YR();
            break;
         case 1029630465:
            this.nY();
            break;
         case 1105816151:
            this.es();
            break;
         case 1116709725:
            this.tW();
            break;
         case 1125005996:
            this.aH();
            break;
         case 1158014871:
            this.qa();
            break;
         case 1172415263:
            this.Ua();
            break;
         case 1206140885:
            this.cY();
            break;
         case 1256698242:
            this.lm();
            break;
         case 1311320894:
            this.qm();
            break;
         case 1360784728:
            this.cv();
            break;
         case 1376556447:
            this.iu();
            break;
         case 1432154034:
            this.Sz();
            break;
         case 1465427985:
            this.fi();
            break;
         case 1474401868:
            this.pQ();
            break;
         case 1477023054:
            this.ZA();
            break;
         case 1488856767:
            this.yu();
            break;
         case 1495610894:
            this.TX();
            break;
         case 1497714135:
            this.Rd();
            break;
         case 1498491051:
            this.Qt();
            break;
         case 1564462299:
            this.AY();
            break;
         case 1567541837:
            this.yc();
            break;
         case 1586414828:
            this.jh();
            break;
         case 1600322159:
            this.ZU();
            break;
         case 1619804715:
            this.LS();
            break;
         case 1648243687:
            this.Of();
            break;
         case 1650328425:
            this.C();
            break;
         case 1667025175:
            this.ND();
            break;
         case 1681513940:
            this.wQ();
            break;
         case 1684260316:
            this.za();
            break;
         case 1697043521:
            this.Cl();
            break;
         case 1713573578:
            this.Dk();
            break;
         case 1739278613:
            this.ou();
            break;
         case 1774565545:
            this.PY();
            break;
         case 1782232408:
            this.tX();
            break;
         case 1793833288:
            this.wr();
            break;
         case 1799892967:
            this.vh();
            break;
         case 1829605578:
            this.Bu();
            break;
         case 1836847084:
            this.SM();
            break;
         case 1856424862:
            this.KF();
            break;
         case 1869495233:
            this.jz();
            break;
         case 1886159338:
            this.FG();
            break;
         case 1887682946:
            this.Uc();
            break;
         case 1911845198:
            this.Ld();
            break;
         case 1950595473:
            this.IY();
            break;
         case 1976222283:
            this.Ri();
            break;
         case 1994000148:
            this.Yp();
            break;
         case 1994699200:
            this.vC();
            break;
         case 2022152394:
            this.JW();
            break;
         case 2027859044:
            this.If();
            break;
         case 2108697204:
            this.wS();
            break;
         case 2114197384:
            this.QZ();
            break;
         case 2141110471:
            this.IJ();
            break;
         default:
            throw new RuntimeException(
               cvm.q(new byte[]{23, 45, 57, 67, 82, 4, 14, 27, 7, 73, 70, 4, 9, 78, 84, 78, 25, 89, 66, 73, 18, 66, 93, 65, 88, 73, 33, 23, 91, 67}, 2, 167)
                  + var1
            );
      }

      this.HF.processStoredReturnAddress(this.JY.Uv.getReturnAddressSlot());
   }

   private void q() {
      this.Ef.skipJniEnvPtr();
      this.Ef.retInt(-1);
   }

   private void RF() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      this.Ef.nextAsPtr();
      this.HF.writePointer(var1, this.JY.Ef);
      this.Ef.retInt(0);
   }

   private void xK() {
      this.Ef.skipJniEnvPtr();
      this.Ef.retInt(0);
   }

   private void Dw() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      int var3 = this.Ef.nextAsInt();
      long var4;
      byte var6;
      if (var3 < 65537) {
         var4 = 0L;
         var6 = -3;
      } else {
         var4 = this.JY.Ef;
         var6 = 0;
      }

      this.HF.writePointer(var1, var4);
      this.Ef.retInt(var6);
   }

   private void Uv() {
      this.RF();
   }

   private void oW() {
      this.Ef.skipJniEnvPtr();
      this.Ef.retInt(65542);
   }

   private void gO() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsPtr();
      this.Ef.nextAsObj();
      this.Ef.nextAsPtr();
      this.Ef.nextAsInt();
      this.Ef.retObj(0);
   }

   private void nf() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      String var3 = cjv.q(this.LK, var1);
      String var4 = cjv.RF(var3);
      Object[] var10000 = new Object[]{var4};
      int var5 = 0;

      try {
         var5 = this.JY.q(var4);
      } catch (DexDecEvalCodeThrownException var8) {
         this.JY.sH = var8.getThrownObjectRef().getObjectReferenceId();
      } catch (DexDecEvaluationException var9) {
         if (!(var9.getCause() instanceof ClassNotFoundException)) {
            throw new cju(
               cvm.q(new byte[]{60, 34, 15, 0, 1, 27, 84, 82, 23, 17, 6, 27, 12, 19, 19, 69, 69, 8, 24, 25, 13, 21, 17, 1, 68, 67, 15, 13, 18, 0, 83}, 1, 127)
                  + var4,
               var9
            );
         }

         IDImm var7 = this.JY.qa.registerObject(var9.getCause());
         this.JY.sH = var7.getObjectReferenceId();
      }

      this.Ef.retInt(var5);
   }

   private void gP() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retInt(0);
   }

   private void za() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retInt(0);
   }

   private void lm() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.nextAsInt();
      this.Ef.nextAsInt();
      this.Ef.retObj(0);
   }

   private void zz() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      String var3 = this.JY.q(var1);
      int var2;
      if (var3.equals("Ljava/lang/Object;")) {
         var2 = 0;
      } else {
         IJLSTypeAdapter var4 = this.JY.qa.getTypeAdapter();
         boolean var5 = (var4.getType(var3).getAccessFlags() & 512) != 0;
         if (var5) {
            var2 = 0;
         } else {
            String var6 = var4.getSupertype(var3);

            try {
               var2 = this.JY.q(var6);
            } catch (DexDecEvaluationException var8) {
               throw new cju(
                  cvm.q(new byte[]{68, 34, 15, 0, 1, 27, 84, 82, 23, 17, 6, 27, 12, 19, 19, 69, 69, 8, 24, 25, 13, 21, 17, 1, 68, 67, 15, 13, 18, 0, 83}, 1, 7)
                     + var3,
                  var8
               );
            }
         }
      }

      this.Ef.retObj(var2);
   }

   private void JY() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsObj();
      String var3 = this.JY.q(var1);
      String var4 = this.JY.q(var2);
      IJLSTypeAdapter var5 = this.JY.qa.getTypeAdapter();
      int var6 = bkm.q(var5, var3, var4) ? 1 : 0;
      this.Ef.retInt(var6);
   }

   private void HF() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.nextAsInt();
      this.Ef.nextAsInt();
      this.Ef.retObj(0);
   }

   private void LK() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      byte var2;
      if (var1 == 0) {
         var2 = -1;
      } else {
         this.JY.sH = var1;
         var2 = 0;
      }

      this.Ef.retInt(var2);
   }

   private void io() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      String var4 = this.JY.q(var1);
      String var5 = cjv.q(this.LK, var2);
      this.JY.qa.getGlobalContext();

      byte var6;
      try {
         IDImm var7 = this.JY.qa.registerObject(var5);
         int var8 = this.JY.qa.createNewInstance(var4 + "-><init>(Ljava/lang/String;)V", Arrays.asList(var7)).getObjectReferenceId();
         this.JY.sH = var8;
         var6 = 0;
      } catch (DexDecEvaluationException var9) {
         var6 = -1;
      }

      this.Ef.retInt(var6);
   }

   private void qa() {
      this.Ef.skipJniEnvPtr();
      int var1 = 0;
      if (this.JY.sH != 0) {
         var1 = this.JY.sH;
      }

      this.Ef.retObj(var1);
   }

   private void Hk() {
      this.Ef.skipJniEnvPtr();
   }

   private void Me() {
      this.Ef.skipJniEnvPtr();
      this.JY.sH = 0;
   }

   private void PV() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      String var3 = cjv.q(this.LK, var1);
      throw new cju("Fatal error: " + var3);
   }

   private void oQ() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsInt();
      this.Ef.retInt(0);
   }

   private void xW() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      this.Ef.retObj(var1);
   }

   private void KT() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      this.Ef.retObj(var1);
   }

   private void Gf() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
   }

   private void Ef() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
   }

   private void cC() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsObj();
      int var3 = var1 == var2 ? 1 : 0;
      this.Ef.retInt(var3);
   }

   private void sH() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      this.Ef.retObj(var1);
   }

   private void CE() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsInt();
      this.Ef.retInt(0);
   }

   private void wF() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retObj(0);
   }

   private void If() {
      this.q("L", 3, 0);
   }

   private void Dz() {
      this.q("L", 3, 1);
   }

   private void mI() {
      this.q("L", 3, 2);
   }

   private void jq() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsInt();

      int var2;
      try {
         var2 = this.JY.qa.getObjectClassId(var1);
      } catch (DexDecEvaluationException var4) {
         throw new cju(var4);
      }

      this.Ef.retInt(var2);
   }

   private void ui() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsObj();

      int var3;
      try {
         var3 = this.JY.qa.getObjectClassId(var1);
      } catch (DexDecEvaluationException var8) {
         throw new cju(var8);
      }

      String var4 = this.JY.q(var3);
      String var5 = this.JY.q(var2);
      IJLSTypeAdapter var6 = this.JY.qa.getTypeAdapter();
      int var7 = bkm.q(var6, var4, var5) ? 1 : 0;
      this.Ef.retInt(var7);
   }

   private void TX() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      long var4 = this.Ef.nextAsPtr();
      String var6 = cjv.q(this.LK, var2);
      String var7 = cjv.q(this.LK, var4);
      String var8 = this.JY.q(var1);
      String var9 = var8 + "->" + var6 + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.JY.xK(var9);
      this.Ef.retObj(var10);
   }

   private void q(String var1, int var2, int var3) {
      this.Ef.skipJniEnvPtr();
      int var5 = 0;
      DInvokeType var4;
      if (var2 == 0) {
         var4 = DInvokeType.VIRTUAL;
         var5 = this.Ef.nextAsObj();
      } else if (var2 == 1) {
         var4 = DInvokeType.DIRECT;
         var5 = this.Ef.nextAsObj();
         this.Ef.nextAsObj();
      } else if (var2 == 2) {
         var4 = DInvokeType.STATIC;
         this.Ef.nextAsObj();
      } else {
         if (var2 != 3) {
            throw new RuntimeException();
         }

         var4 = DInvokeType.NEW;
         this.Ef.nextAsObj();
      }

      int var6 = this.Ef.nextAsObj();
      String var7 = this.JY.xK(var6);
      JvmMethodSig var8 = JvmMethodSig.parse(var7);
      IDGlobalContext var9 = this.JY.qa.getGlobalContext();
      ArrayList var10 = new ArrayList();
      if (var2 == 0 || var2 == 1) {
         var10.add(var9.createRef(var5));
      }

      if (var3 == 0) {
         for (String var12 : var8.partypes) {
            var10.add(switch (var12.charAt(0)) {
               case 'B', 'C', 'I', 'S', 'Z' -> var9.createImm(this.Ef.nextAsInt(), var9.getTypeFactory().createType(var12));
               case 'D' -> var9.createDouble(this.Ef.nextAsDouble());
               default -> throw new RuntimeException();
               case 'F' -> {
                  float var42;
                  if (this.Hk) {
                     var42 = (float)this.Ef.nextAsDouble();
                  } else {
                     var42 = this.Ef.nextAsFloat();
                  }

                  yield var9.createFloat(var42);
               }
               case 'J' -> var9.createLong(this.Ef.nextAsLong());
               case 'L', '[' -> var9.createRef(this.Ef.nextAsObj());
            });
         }
      } else if (var3 != 1) {
         if (var3 != 2) {
            throw new RuntimeException();
         }

         long var27 = this.Ef.nextAsPtr();
         INativeType var37 = this.JY.Dw.getType("jvalue");
         VMReader var41 = new VMReader(this.LK);

         for (String var45 : var8.partypes) {
            VMReader.Buf var46 = var41.read(var27, var37);
            var27 += var37.getSize();

            var10.add(switch (var45.charAt(0)) {
               case 'B', 'C', 'I', 'S', 'Z' -> var9.createImm(var46.getInt("i"), var9.getTypeFactory().createType(var45));
               case 'D' -> var9.createDouble(Double.longBitsToDouble(var46.getLong("d")));
               default -> throw new RuntimeException();
               case 'F' -> var9.createFloat(Float.intBitsToFloat(var46.getInt("f")));
               case 'J' -> var9.createLong(var46.getLong("j"));
               case 'L', '[' -> var9.createRef(var46.getInt("l"));
            });
         }
      } else {
         long var11 = this.Ef.nextAsPtr();

         for (String var14 : var8.partypes) {
            if (var14.equals("F") || var14.equals("D")) {
               throw new RuntimeException();
            }
         }

         ArrayList var36 = new ArrayList();
         INativeType var40 = this.JY.Dw.getType("jvalue");
         VMReader var15 = new VMReader(this.LK);
         if (this.qa == ProcessorType.X86_64) {
            throw new RuntimeException();
         }

         if (this.qa != ProcessorType.ARM64) {
            throw new RuntimeException();
         }

         long var16;
         try {
            long var18 = this.LK.readPointer(var11 + 8L);
            int var20 = this.LK.readInt(var11 + 24L);
            var16 = var18 + var20;
         } catch (MemoryException var26) {
            throw new RuntimeException(var26);
         }

         for (String var50 : var8.partypes) {
            VMReader.Buf var23 = var15.read(var16, var40);

            long var21 = switch (var50.charAt(0)) {
               case 'B', 'C', 'I', 'S', 'Z' -> var23.getInt("i");
               default -> throw new RuntimeException();
               case 'J' -> var23.getLong("j");
               case 'L', '[' -> var23.getInt("l");
            };
            var16 += 8L;
            var36.add(var21);
         }

         int var44 = 0;

         for (String var47 : var8.partypes) {
            long var49 = (Long)var36.get(var44++);

            var10.add(switch (var47.charAt(0)) {
               case 'B', 'C', 'I', 'S', 'Z' -> var9.createImm(var49, var9.getTypeFactory().createType(var47));
               case 'D' -> var9.createDouble(Double.longBitsToDouble(var49));
               default -> throw new RuntimeException();
               case 'F' -> var9.createFloat(Float.intBitsToFloat((int)var49));
               case 'J' -> var9.createLong(var49);
               case 'L', '[' -> var9.createRef((int)var49);
            });
         }
      }

      IDImm var29;
      try {
         if (var4 == DInvokeType.NEW) {
            var29 = this.JY.qa.createNewInstance(var7, var10);
         } else {
            var29 = this.JY.qa.invokeMethod(var7, var10, var4);
         }
      } catch (DexDecEvalCodeThrownException var24) {
         int var39 = var24.getThrownObjectRef().getObjectReferenceId();
         this.JY.sH = var39;
         return;
      } catch (DexDecEvaluationException var25) {
         throw new cju(var25);
      }

      if (var4 == DInvokeType.NEW) {
         int var35 = (int)var29.getRawValue();
         this.Ef.retObj(var35);
      } else {
         switch (var8.rettype.charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
               int var34 = (int)var29.getRawValue();
               this.Ef.retInt(var34);
               break;
            case 'D':
               double var33 = Double.longBitsToDouble(var29.getRawValue());
               this.Ef.retDouble(var33);
               break;
            case 'E':
            case 'G':
            case 'H':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'T':
            case 'U':
            case 'W':
            case 'X':
            case 'Y':
            case '[':
            default:
               int var32 = (int)var29.getRawValue();
               this.Ef.retObj(var32);
               break;
            case 'F':
               float var31 = Float.intBitsToFloat((int)var29.getRawValue());
               this.Ef.retFloat(var31);
               break;
            case 'J':
               long var30 = var29.getRawValue();
               this.Ef.retLong(var30);
            case 'V':
         }
      }
   }

   private void Rr() {
      this.q("L", 0, 0);
   }

   private void EB() {
      this.q("L", 0, 1);
   }

   private void Xo() {
      this.q("L", 0, 2);
   }

   private void Bu() {
      this.q("Z", 0, 0);
   }

   private void IN() {
      this.q("Z", 0, 1);
   }

   private void rL() {
      this.q("Z", 0, 2);
   }

   private void eJ() {
      this.q("B", 0, 0);
   }

   private void YN() {
      this.q("B", 0, 1);
   }

   private void Rv() {
      this.q("B", 0, 2);
   }

   private void zx() {
      this.q("C", 0, 0);
   }

   private void ZT() {
      this.q("C", 0, 1);
   }

   private void Ri() {
      this.q("C", 0, 2);
   }

   private void GY() {
      this.q("S", 0, 0);
   }

   private void Wx() {
      this.q("S", 0, 1);
   }

   private void AB() {
      this.q("S", 0, 2);
   }

   private void CY() {
      this.q("I", 0, 0);
   }

   private void WI() {
      this.q("I", 0, 1);
   }

   private void Tq() {
      this.q("I", 0, 2);
   }

   private void Yp() {
      this.q("J", 0, 0);
   }

   private void Gu() {
      this.q("J", 0, 1);
   }

   private void nY() {
      this.q("J", 0, 2);
   }

   private void lF() {
      this.q("F", 0, 0);
   }

   private void nq() {
      this.q("F", 0, 1);
   }

   private void NX() {
      this.q("F", 0, 2);
   }

   private void br() {
      this.q("D", 0, 0);
   }

   private void tW() {
      this.q("D", 0, 1);
   }

   private void ZA() {
      this.q("D", 0, 2);
   }

   private void Ov() {
      this.q("V", 0, 0);
   }

   private void Lj() {
      this.q("V", 0, 1);
   }

   private void nv() {
      this.q("V", 0, 2);
   }

   private void LL() {
      this.q("L", 1, 0);
   }

   private void PQ() {
      this.q("L", 1, 1);
   }

   private void fQ() {
      this.q("L", 1, 2);
   }

   private void fi() {
      this.q("Z", 1, 0);
   }

   private void bl() {
      this.q("Z", 1, 1);
   }

   private void jb() {
      this.q("Z", 1, 2);
   }

   private void pQ() {
      this.q("B", 1, 0);
   }

   private void kf() {
      this.q("B", 1, 1);
   }

   private void GM() {
      this.q("B", 1, 2);
   }

   private void TQ() {
      this.q("C", 1, 0);
   }

   private void Yw() {
      this.q("C", 1, 1);
   }

   private void IY() {
      this.q("C", 1, 2);
   }

   private void qR() {
      this.q("S", 1, 0);
   }

   private void YA() {
      this.q("S", 1, 1);
   }

   private void fw() {
      this.q("S", 1, 2);
   }

   private void Wp() {
      this.q("I", 1, 0);
   }

   private void cY() {
      this.q("I", 1, 1);
   }

   private void PY() {
      this.q("I", 1, 2);
   }

   private void cR() {
      this.q("J", 1, 0);
   }

   private void eC() {
      this.q("J", 1, 1);
   }

   private void ND() {
      this.q("J", 1, 2);
   }

   private void Qu() {
      this.q("F", 1, 0);
   }

   private void jh() {
      this.q("F", 1, 1);
   }

   private void Jf() {
      this.q("F", 1, 2);
   }

   private void vC() {
      this.q("D", 1, 0);
   }

   private void of() {
      this.q("D", 1, 1);
   }

   private void os() {
      this.q("D", 1, 2);
   }

   private void iu() {
      this.q("V", 1, 0);
   }

   private void fn() {
      this.q("V", 1, 1);
   }

   private void ZU() {
      this.q("V", 1, 2);
   }

   private void Sz() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      long var4 = this.Ef.nextAsPtr();
      String var6 = cjv.q(this.LK, var2);
      String var7 = cjv.q(this.LK, var4);
      String var8 = this.JY.q(var1);
      String var9 = var8 + "->" + var6 + ":" + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.JY.RF(var9);
      this.Ef.retObj(var10);
   }

   private void q(char var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      int var3 = this.Ef.nextAsObj();
      String var4 = this.JY.RF(var3);

      long var5;
      try {
         IDGlobalContext var7 = this.JY.qa.getGlobalContext();
         IDImm var8 = this.JY.qa.getInstanceField(var4, var7.createRef(var2));
         var5 = var8.getRawValue();
      } catch (DexDecEvaluationException var9) {
         throw new cju(var9);
      }

      switch (var1) {
         case 'B':
            this.Ef.retInt((byte)var5);
            break;
         case 'C':
            this.Ef.retInt((char)var5);
            break;
         case 'D':
            this.Ef.retDouble(Double.longBitsToDouble(var5));
            break;
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            throw new RuntimeException();
         case 'F':
            this.Ef.retFloat(Float.intBitsToFloat((int)var5));
            break;
         case 'I':
            this.Ef.retInt((int)var5);
            break;
         case 'J':
            this.Ef.retLong(var5);
            break;
         case 'L':
            this.Ef.retObj((int)var5);
            break;
         case 'S':
            this.Ef.retInt((short)var5);
            break;
         case 'Z':
            this.Ef.retInt((int)var5 & 1);
      }
   }

   private void fq() {
      this.q('L');
   }

   private void mJ() {
      this.q('Z');
   }

   private void Bs() {
      this.q('B');
   }

   private void rV() {
      this.q('C');
   }

   private void WX() {
      this.q('S');
   }

   private void CB() {
      this.q('I');
   }

   private void C() {
      this.q('J');
   }

   private void GC() {
      this.q('F');
   }

   private void KF() {
      this.q('D');
   }

   private void RF(char var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      int var3 = this.Ef.nextAsObj();

      long var4 = switch (var1) {
         case 'B' -> (byte)this.Ef.nextAsInt();
         case 'C' -> this.Ef.nextAsInt() & 65535L;
         case 'D' -> Double.doubleToLongBits(this.Ef.nextAsDouble());
         default -> throw new RuntimeException();
         case 'F' -> Float.floatToIntBits(this.Ef.nextAsFloat());
         case 'I' -> this.Ef.nextAsInt();
         case 'J' -> this.Ef.nextAsLong();
         case 'L' -> this.Ef.nextAsObj();
         case 'S' -> (short)this.Ef.nextAsInt();
         case 'Z' -> this.Ef.nextAsInt() & 1;
      };
      String var6 = this.JY.RF(var3);

      try {
         IDGlobalContext var7 = this.JY.qa.getGlobalContext();
         IDImm var8 = var7.createImm(var4, var7.getTypeFactory().letterToType(var1));
         this.JY.qa.setInstanceField(var6, var7.createRef(var2), var8);
      } catch (DexDecEvaluationException var9) {
         throw new cju(var9);
      }
   }

   private void rk() {
      this.RF('L');
   }

   private void cy() {
      this.RF('Z');
   }

   private void jk() {
      this.RF('B');
   }

   private void Cl() {
      this.RF('C');
   }

   private void hM() {
      this.RF('S');
   }

   private void kv() {
      this.RF('I');
   }

   private void oS() {
      this.RF('J');
   }

   private void FG() {
      this.RF('F');
   }

   private void Al() {
      this.RF('D');
   }

   private void Kn() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      long var4 = this.Ef.nextAsPtr();
      String var6 = cjv.q(this.LK, var2);
      String var7 = cjv.q(this.LK, var4);
      String var8 = this.JY.q(var1);
      String var9 = var8 + "->" + var6 + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.JY.xK(var9);
      this.Ef.retObj(var10);
   }

   private void vh() {
      this.q("L", 2, 0);
   }

   private void Rd() {
      this.q("L", 2, 1);
   }

   private void Eq() {
      this.q("L", 2, 2);
   }

   private void hP() {
      this.q("Z", 2, 0);
   }

   private void wN() {
      this.q("Z", 2, 1);
   }

   private void Uc() {
      this.q("Z", 2, 2);
   }

   private void TB() {
      this.q("B", 2, 0);
   }

   private void dg() {
      this.q("B", 2, 1);
   }

   private void hw() {
      this.q("B", 2, 2);
   }

   private void gm() {
      this.q("C", 2, 0);
   }

   private void uY() {
      this.q("C", 2, 1);
   }

   private void sc() {
      this.q("C", 2, 2);
   }

   private void wQ() {
      this.q("S", 2, 0);
   }

   private void Oj() {
      this.q("S", 2, 1);
   }

   private void VW() {
      this.q("S", 2, 2);
   }

   private void ap() {
      this.q("I", 2, 0);
   }

   private void RL() {
      this.q("I", 2, 1);
   }

   private void hy() {
      this.q("I", 2, 2);
   }

   private void Xi() {
      this.q("J", 2, 0);
   }

   private void Ag() {
      this.q("J", 2, 1);
   }

   private void rp() {
      this.q("J", 2, 2);
   }

   private void CW() {
      this.q("F", 2, 0);
   }

   private void qm() {
      this.q("F", 2, 1);
   }

   private void LR() {
      this.q("F", 2, 2);
   }

   private void Uz() {
      this.q("D", 2, 0);
   }

   private void dF() {
      this.q("D", 2, 1);
   }

   private void kk() {
      this.q("D", 2, 2);
   }

   private void Rc() {
      this.q("V", 2, 0);
   }

   private void jz() {
      this.q("V", 2, 1);
   }

   private void MT() {
      this.q("V", 2, 2);
   }

   private void bY() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      long var4 = this.Ef.nextAsPtr();
      String var6 = cjv.q(this.LK, var2);
      String var7 = cjv.q(this.LK, var4);
      String var8 = this.JY.q(var1);
      String var9 = var8 + "->" + var6 + ":" + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.JY.RF(var9);
      this.Ef.retObj(var10);
   }

   private void xK(char var1) {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsObj();
      String var3 = this.JY.RF(var2);

      long var4;
      try {
         IDImm var6 = this.JY.qa.getStaticField(var3);
         var4 = var6.getRawValue();
      } catch (DexDecEvaluationException var7) {
         throw new cju(var7);
      }

      switch (var1) {
         case 'B':
            this.Ef.retInt((byte)var4);
            break;
         case 'C':
            this.Ef.retInt((char)var4);
            break;
         case 'D':
            this.Ef.retDouble(Double.longBitsToDouble(var4));
            break;
         case 'E':
         case 'G':
         case 'H':
         case 'K':
         case 'M':
         case 'N':
         case 'O':
         case 'P':
         case 'Q':
         case 'R':
         case 'T':
         case 'U':
         case 'V':
         case 'W':
         case 'X':
         case 'Y':
         default:
            throw new RuntimeException();
         case 'F':
            this.Ef.retFloat(Float.intBitsToFloat((int)var4));
            break;
         case 'I':
            this.Ef.retInt((int)var4);
            break;
         case 'J':
            this.Ef.retLong(var4);
            break;
         case 'L':
            this.Ef.retObj((int)var4);
            break;
         case 'S':
            this.Ef.retInt((short)var4);
            break;
         case 'Z':
            this.Ef.retInt((int)var4 & 1);
      }
   }

   private void LS() {
      this.xK('L');
   }

   private void fG() {
      this.xK('Z');
   }

   private void cO() {
      this.xK('B');
   }

   private void wr() {
      this.xK('C');
   }

   private void pe() {
      this.xK('S');
   }

   private void Gg() {
      this.xK('I');
   }

   private void CK() {
      this.xK('J');
   }

   private void PW() {
      this.xK('F');
   }

   private void zm() {
      this.xK('D');
   }

   private void Dw(char var1) {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsObj();

      long var3 = switch (var1) {
         case 'B' -> (byte)this.Ef.nextAsInt();
         case 'C' -> this.Ef.nextAsInt() & 65535L;
         case 'D' -> Double.doubleToLongBits(this.Ef.nextAsDouble());
         default -> throw new RuntimeException();
         case 'F' -> Float.floatToIntBits(this.Ef.nextAsFloat());
         case 'I' -> this.Ef.nextAsInt();
         case 'J' -> this.Ef.nextAsLong();
         case 'L' -> this.Ef.nextAsObj();
         case 'S' -> (short)this.Ef.nextAsInt();
         case 'Z' -> this.Ef.nextAsInt() & 1;
      };
      String var5 = this.JY.RF(var2);

      try {
         IDGlobalContext var6 = this.JY.qa.getGlobalContext();
         IDImm var7 = var6.createImm(var3, var6.getTypeFactory().letterToType(var1));
         this.JY.qa.setStaticField(var5, var7);
      } catch (DexDecEvaluationException var8) {
         throw new cju(var8);
      }
   }

   private void Wn() {
      this.Dw('L');
   }

   private void eG() {
      this.Dw('Z');
   }

   private void Id() {
      this.Dw('B');
   }

   private void Dk() {
      this.Dw('C');
   }

   private void dS() {
      this.Dw('S');
   }

   private void cb() {
      this.Dw('I');
   }

   private void BU() {
      this.Dw('J');
   }

   private void xG() {
      this.Dw('F');
   }

   private void wS() {
      this.Dw('D');
   }

   private void Oz() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      int var3 = this.Ef.nextAsInt();
      int var4 = var3 * 2;
      byte[] var5 = new byte[var4];
      VirtualMemoryUtil.readBytesSafe(this.LK, var1, var5.length, var5, 0, true);
      char[] var6 = new char[var3];
      int var7 = 0;

      for (byte var8 = 0; var8 < var4; var8 += 2) {
         var6[var7] = (char)(var5[var8] & 255 | var5[var8 + 1] << 8 & 0xFF00);
         var7++;
      }

      String var10 = new String(var6);
      int var9 = this.JY.qa.registerObject(var10).getObjectReferenceId();
      this.Ef.retObj(var9);
   }

   private void yn() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();

      int var2;
      try {
         String var3 = (String)this.JY.qa.getObject(var1);
         var2 = var3.length();
      } catch (DexDecEvaluationException var4) {
         throw new cju(var4);
      }

      this.Ef.retInt(var2);
   }

   private void es() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      if (var2 != 0L) {
         try {
            this.LK.writeInt(var2, 1);
         } catch (MemoryException var9) {
            throw new cju(var9);
         }
      }

      long var4;
      try {
         String var6 = (String)this.JY.qa.getObject(var1);
         char[] var7 = var6.toCharArray();
         var4 = this.HF.heapAlloc(var7.length * 2);
         VirtualMemoryUtil.writeChars(this.LK, var4, var7, 0, var7.length);
      } catch (DexDecEvaluationException var8) {
         throw new cju(var8);
      }

      this.Ef.retPtr(var4);
   }

   private void o() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      long var1 = this.Ef.nextAsPtr();
      this.HF.heapFree(var1);
   }

   private void gl() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      String var3 = cjv.q(this.LK, var1);
      Object[] var10000 = new Object[]{var3};
      int var4 = this.JY.qa.registerObject(var3).getObjectReferenceId();
      this.Ef.retObj(var4);
   }

   private void tX() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();

      int var2;
      try {
         String var3 = (String)this.JY.qa.getObject(var1);
         var2 = DexUtil.stringToMUTF8(var3, false).length;
      } catch (DexDecEvaluationException var4) {
         throw new cju(var4);
      }

      this.Ef.retInt(var2);
   }

   private void Qt() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      if (var2 != 0L) {
         try {
            this.LK.writeInt(var2, 1);
         } catch (MemoryException var8) {
            throw new cju(var8);
         }
      }

      long var4 = 0L;

      try {
         String var6 = (String)this.JY.qa.getObject(var1);
         if (var6 != null) {
            byte[] var7 = DexUtil.stringToMUTF8(var6);
            var4 = this.HF.heapAlloc(var7.length);
            VirtualMemoryUtil.writeBytes(this.LK, var4, var7, 0, var7.length);
         }
      } catch (DexDecEvaluationException var9) {
         throw new cju(var9);
      }

      this.Ef.retPtr(var4);
   }

   private void JW() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      long var1 = this.Ef.nextAsPtr();
      this.HF.heapFree(var1);
   }

   private void Ub() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();

      int var2;
      try {
         IDGlobalContext var3 = this.JY.qa.getGlobalContext();
         var2 = this.JY.qa.getArrayObjectLength(var3.createRef(var1));
      } catch (DexDecEvaluationException var4) {
         throw new cju(var4);
      }

      this.Ef.retInt(var2);
   }

   private void tb() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsInt();
      int var2 = this.Ef.nextAsObj();
      int var3 = this.Ef.nextAsObj();
      String var4 = this.JY.q(var2);
      IDGlobalContext var5 = this.JY.qa.getGlobalContext();
      IJavaType var6 = var5.getTypeFactory().createType("[" + var4);
      Object var7 = Collections.emptyList();
      if (var3 != 0) {
         var7 = new ArrayList(var1);

         for (int var8 = 0; var8 < var1; var8++) {
            var7.add(var5.createRef(var3));
         }
      }

      Object[] var10000 = new Object[]{var1, var4, var3};

      int var11;
      try {
         var11 = this.JY.qa.createArray(var6, var1, (List)var7).getObjectReferenceId();
      } catch (DexDecEvaluationException var10) {
         throw new cju(var10);
      }

      this.Ef.retObj(var11);
   }

   private void yW() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsInt();

      int var3;
      try {
         IDGlobalContext var4 = this.JY.qa.getGlobalContext();
         var3 = this.JY.qa.getArrayElement(var4.createRef(var1), var2).getObjectReferenceId();
      } catch (DexDecEvaluationException var5) {
         throw new cju(var5);
      }

      this.Ef.retObj(var3);
   }

   private void JF() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsInt();
      int var3 = this.Ef.nextAsObj();

      try {
         IDGlobalContext var4 = this.JY.qa.getGlobalContext();
         this.JY.qa.setArrayElement(var4.createRef(var1), var2, var4.createRef(var3));
      } catch (DexDecEvaluationException var5) {
         throw new cju(var5);
      }
   }

   private void RF(String var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsInt();
      IJavaTypeFactory var3 = this.JY.qa.getGlobalContext().getTypeFactory();
      IJavaType var4 = var3.createType(var1);

      int var5;
      try {
         var5 = this.JY.qa.createArray(var4, var2, Arrays.asList()).getObjectReferenceId();
      } catch (DexDecEvaluationException var7) {
         throw new cju(var7);
      }

      this.Ef.retObj(var5);
   }

   private void uz() {
      this.RF("[Z");
   }

   private void Xz() {
      this.RF("[B");
   }

   private void iK() {
      this.RF("[C");
   }

   private void ZE() {
      this.RF("[S");
   }

   private void Jh() {
      this.RF("[I");
   }

   private void iO() {
      this.RF("[J");
   }

   private void Qe() {
      this.RF("[F");
   }

   private void dW() {
      this.RF("[D");
   }

   private String q(Object var1) {
      if (var1 instanceof boolean[]) {
         return "[Z";
      } else if (var1 instanceof byte[]) {
         return "[B";
      } else if (var1 instanceof char[]) {
         return "[C";
      } else if (var1 instanceof short[]) {
         return "[S";
      } else if (var1 instanceof int[]) {
         return "[I";
      } else if (var1 instanceof long[]) {
         return "[J";
      } else if (var1 instanceof float[]) {
         return "[F";
      } else if (var1 instanceof double[]) {
         return "[D";
      } else {
         throw new RuntimeException();
      }
   }

   private void xK(String var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      long var3 = this.Ef.nextAsPtr();
      if (var3 != 0L) {
         try {
            this.LK.writeInt(var3, 1);
         } catch (MemoryException var12) {
            throw new cju(var12);
         }
      }

      long var5;
      try {
         IDGlobalContext var7 = this.JY.qa.getGlobalContext();
         Object var8 = this.JY.qa.getArrayObject(var7.createRef(var2));
         if (var1 == null) {
            var1 = this.q(var8);
         }

         switch (var1) {
            case "[Z":
               boolean[] var20 = (boolean[])var8;
               var5 = this.HF.heapAlloc(var20.length);
               VirtualMemoryUtil.writeBooleans(this.LK, var5, var20, 0, var20.length);
               break;
            case "[B":
               byte[] var19 = (byte[])var8;
               var5 = this.HF.heapAlloc(var19.length);
               VirtualMemoryUtil.writeBytes(this.LK, var5, var19, 0, var19.length);
               break;
            case "[C":
               char[] var18 = (char[])var8;
               var5 = this.HF.heapAlloc(var18.length * 2);
               VirtualMemoryUtil.writeChars(this.LK, var5, var18, 0, var18.length);
               break;
            case "[S":
               short[] var17 = (short[])var8;
               var5 = this.HF.heapAlloc(var17.length * 2);
               VirtualMemoryUtil.writeShorts(this.LK, var5, var17, 0, var17.length);
               break;
            case "[I":
               int[] var16 = (int[])var8;
               var5 = this.HF.heapAlloc(var16.length * 4);
               VirtualMemoryUtil.writeInts(this.LK, var5, var16, 0, var16.length);
               break;
            case "[J":
               long[] var15 = (long[])var8;
               var5 = this.HF.heapAlloc(var15.length * 8);
               VirtualMemoryUtil.writeLongs(this.LK, var5, var15, 0, var15.length);
               break;
            case "[F":
               float[] var14 = (float[])var8;
               var5 = this.HF.heapAlloc(var14.length * 4);
               VirtualMemoryUtil.writeFloats(this.LK, var5, var14, 0, var14.length);
               break;
            case "[D":
               double[] var11 = (double[])var8;
               var5 = this.HF.heapAlloc(var11.length * 8);
               VirtualMemoryUtil.writeDoubles(this.LK, var5, var11, 0, var11.length);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var13) {
         throw new cju(var13);
      }

      this.Ef.retPtr(var5);
   }

   private void HK() {
      this.xK("[Z");
   }

   private void uw() {
      this.xK("[B");
   }

   private void fe() {
      this.xK("[C");
   }

   private void Kl() {
      this.xK("[S");
   }

   private void So() {
      this.xK("[I");
   }

   private void AG() {
      this.xK("[J");
   }

   private void er() {
      this.xK("[F");
   }

   private void SM() {
      this.xK("[D");
   }

   private void Dw(String var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      long var3 = this.Ef.nextAsPtr();
      int var5 = this.Ef.nextAsInt();
      if (var5 == 2) {
         this.HF.heapFree(var3);
      } else {
         long var6 = var3;

         try {
            IDGlobalContext var8 = this.JY.qa.getGlobalContext();
            Object var9 = this.JY.qa.getArrayObject(var8.createRef(var2));
            if (var1 == null) {
               var1 = this.q(var9);
            }

            switch (var1) {
               case "[Z":
                  boolean[] var20 = (boolean[])var9;
                  VirtualMemoryUtil.readBooleans(this.LK, var6, var20, 0, var20.length);
                  break;
               case "[B":
                  byte[] var19 = (byte[])var9;
                  VirtualMemoryUtil.readBytes(this.LK, var6, var19, 0, var19.length);
                  break;
               case "[C":
                  char[] var18 = (char[])var9;
                  VirtualMemoryUtil.readChars(this.LK, var6, var18, 0, var18.length);
                  break;
               case "[S":
                  short[] var17 = (short[])var9;
                  VirtualMemoryUtil.readShorts(this.LK, var6, var17, 0, var17.length);
                  break;
               case "[I":
                  int[] var16 = (int[])var9;
                  VirtualMemoryUtil.readInts(this.LK, var6, var16, 0, var16.length);
                  break;
               case "[J":
                  long[] var15 = (long[])var9;
                  VirtualMemoryUtil.readLongs(this.LK, var6, var15, 0, var15.length);
                  break;
               case "[F":
                  float[] var14 = (float[])var9;
                  VirtualMemoryUtil.readFloats(this.LK, var6, var14, 0, var14.length);
                  break;
               case "[D":
                  double[] var12 = (double[])var9;
                  VirtualMemoryUtil.readDoubles(this.LK, var6, var12, 0, var12.length);
                  break;
               default:
                  throw new RuntimeException();
            }
         } catch (DexDecEvaluationException var13) {
            throw new cju(var13);
         }

         if (var5 == 0) {
            this.HF.heapFree(var3);
         }
      }
   }

   private void bj() {
      this.Dw("[Z");
   }

   private void GO() {
      this.Dw("[B");
   }

   private void QZ() {
      this.Dw("[C");
   }

   private void Up() {
      this.Dw("[S");
   }

   private void HO() {
      this.Dw("[I");
   }

   private void cv() {
      this.Dw("[J");
   }

   private void lk() {
      this.Dw("[F");
   }

   private void sa() {
      this.Dw("[D");
   }

   private void Uv(String var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      int var3 = this.Ef.nextAsInt();
      int var4 = this.Ef.nextAsInt();
      long var5 = this.Ef.nextAsPtr();
      long var7 = var5;

      try {
         IDGlobalContext var9 = this.JY.qa.getGlobalContext();
         Object var10 = this.JY.qa.getArrayObject(var9.createRef(var2));
         switch (var1) {
            case "[Z":
               boolean[] var21 = (boolean[])var10;
               VirtualMemoryUtil.writeBooleans(this.LK, var7, var21, var3, var4);
               break;
            case "[B":
               byte[] var20 = (byte[])var10;
               VirtualMemoryUtil.writeBytes(this.LK, var7, var20, var3, var4);
               break;
            case "[C":
               char[] var19 = (char[])var10;
               VirtualMemoryUtil.writeChars(this.LK, var7, var19, var3, var4);
               break;
            case "[S":
               short[] var18 = (short[])var10;
               VirtualMemoryUtil.writeShorts(this.LK, var7, var18, var3, var4);
               break;
            case "[I":
               int[] var17 = (int[])var10;
               VirtualMemoryUtil.writeInts(this.LK, var7, var17, var3, var4);
               break;
            case "[J":
               long[] var16 = (long[])var10;
               VirtualMemoryUtil.writeLongs(this.LK, var7, var16, var3, var4);
               break;
            case "[F":
               float[] var15 = (float[])var10;
               VirtualMemoryUtil.writeFloats(this.LK, var7, var15, var3, var4);
               break;
            case "[D":
               double[] var13 = (double[])var10;
               VirtualMemoryUtil.writeDoubles(this.LK, var7, var13, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var14) {
         throw new cju(var14);
      }
   }

   private void WJ() {
      this.Uv("[Z");
   }

   private void pL() {
      this.Uv("[B");
   }

   private void aH() {
      this.Uv("[C");
   }

   private void yc() {
      this.Uv("[S");
   }

   private void eb() {
      this.Uv("[I");
   }

   private void zj() {
      this.Uv("[J");
   }

   private void aV() {
      this.Uv("[F");
   }

   private void Qo() {
      this.Uv("[D");
   }

   private void oW(String var1) {
      this.Ef.skipJniEnvPtr();
      int var2 = this.Ef.nextAsObj();
      int var3 = this.Ef.nextAsInt();
      int var4 = this.Ef.nextAsInt();
      long var5 = this.Ef.nextAsPtr();
      long var7 = var5;

      try {
         IDGlobalContext var9 = this.JY.qa.getGlobalContext();
         Object var10 = this.JY.qa.getArrayObject(var9.createRef(var2));
         switch (var1) {
            case "[Z":
               boolean[] var21 = (boolean[])var10;
               VirtualMemoryUtil.readBooleans(this.LK, var7, var21, var3, var4);
               break;
            case "[B":
               byte[] var20 = (byte[])var10;
               VirtualMemoryUtil.readBytes(this.LK, var7, var20, var3, var4);
               break;
            case "[C":
               char[] var19 = (char[])var10;
               VirtualMemoryUtil.readChars(this.LK, var7, var19, var3, var4);
               break;
            case "[S":
               short[] var18 = (short[])var10;
               VirtualMemoryUtil.readShorts(this.LK, var7, var18, var3, var4);
               break;
            case "[I":
               int[] var17 = (int[])var10;
               VirtualMemoryUtil.readInts(this.LK, var7, var17, var3, var4);
               break;
            case "[J":
               long[] var16 = (long[])var10;
               VirtualMemoryUtil.readLongs(this.LK, var7, var16, var3, var4);
               break;
            case "[F":
               float[] var15 = (float[])var10;
               VirtualMemoryUtil.readFloats(this.LK, var7, var15, var3, var4);
               break;
            case "[D":
               double[] var13 = (double[])var10;
               VirtualMemoryUtil.readDoubles(this.LK, var7, var13, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var14) {
         throw new cju(var14);
      }
   }

   private void lN() {
      this.oW("[Z");
   }

   private void gT() {
      this.oW("[B");
   }

   private void qr() {
      this.oW("[C");
   }

   private void IJ() {
      this.oW("[S");
   }

   private void Of() {
      this.oW("[I");
   }

   private void AN() {
      this.oW("[J");
   }

   private void RW() {
      this.oW("[F");
   }

   private void YR() {
      this.oW("[D");
   }

   private void fN() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      long var2 = this.Ef.nextAsPtr();
      int var4 = this.Ef.nextAsInt();
      String var5 = this.JY.q(var1);
      VMReader var6 = new VMReader(this.LK);
      long var7 = var2;
      int var9 = var4;

      for (int var10 = 0; var10 < var9; var10++) {
         VMReader.Buf var11 = var6.read(var7, this.JY.oQ);
         String var12 = cjv.q(this.LK, var11.getPtr("name"));
         String var13 = cjv.q(this.LK, var11.getPtr("signature"));
         long var14 = var11.getPtr("fnPtr");
         this.JY.q(var5, var12, var13, var14);
         var7 += this.JY.oQ.getSize();
      }

      this.Ef.retInt(0);
   }

   private void GH() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      String var2 = this.JY.q(var1);
      this.JY.Dw(var2);
      this.Ef.retInt(0);
   }

   private void BY() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retInt(0);
   }

   private void fK() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retInt(0);
   }

   private void ou() {
      this.Ef.skipJniEnvPtr();
      long var1 = this.Ef.nextAsPtr();
      this.HF.writePointer(var1, this.JY.KT);
      this.Ef.retInt(0);
   }

   private void DP() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsInt();
      int var3 = this.Ef.nextAsInt();
      long var4 = this.Ef.nextAsPtr();

      String var6;
      try {
         var6 = (String)this.JY.qa.getObject(var1);
      } catch (DexDecEvaluationException var9) {
         throw new cju(var9);
      }

      String var7 = var6.substring(var2, var2 + var3);
      char[] var8 = var7.toCharArray();
      VirtualMemoryUtil.writeChars(this.LK, var4, var8, 0, var8.length);
   }

   private void lA() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      int var2 = this.Ef.nextAsInt();
      int var3 = this.Ef.nextAsInt();
      long var4 = this.Ef.nextAsPtr();

      String var6;
      try {
         var6 = (String)this.JY.qa.getObject(var1);
      } catch (DexDecEvaluationException var9) {
         throw new cju(var9);
      }

      String var7 = var6.substring(var2, var2 + var3);
      byte[] var8 = DexUtil.stringToMUTF8(var7, false);
      VirtualMemoryUtil.writeBytes(this.LK, var4, var8, 0, var8.length);
   }

   private void yu() {
      this.xK(null);
   }

   private void lz() {
      this.Dw(null);
   }

   private void Nu() {
      this.es();
   }

   private void YT() {
      this.o();
   }

   private void AY() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      this.Ef.retObj(var1);
   }

   private void Ld() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
   }

   private void XV() {
      this.Ef.skipJniEnvPtr();
      byte var1 = 0;
      if (this.JY.sH != 0) {
         var1 = 1;
      }

      this.Ef.retInt(var1);
   }

   private void NY() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsPtr();
      this.Ef.nextAsLong();
      this.Ef.retObj(0);
   }

   private void xf() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retPtr(0L);
   }

   private void Ua() {
      this.Ef.skipJniEnvPtr();
      this.Ef.nextAsObj();
      this.Ef.retLong(0L);
   }

   private void jT() {
      this.Ef.skipJniEnvPtr();
      int var1 = this.Ef.nextAsObj();
      byte var2 = 0;

      try {
         if (this.JY.qa.getObject(var1) != null) {
            var2 = 2;
         }
      } catch (DexDecEvaluationException var3) {
      }

      this.Ef.retInt(var2);
   }
}
