package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.DexUtil;
import com.pnfsoftware.jeb.core.units.code.android.IJLSTypeAdapter;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
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
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Formatter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ceg implements IEEmulatorHooks {
   cdz pC;
   EEmulator A;
   IVirtualMemory kS;
   IEGlobalContext wS;
   ProcessorType UT;
   boolean E;
   private RoutineIOHandler sY;
   private Map ys = new HashMap();

   ceg(cdz var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var1;
         this.A = var1.fI();
         this.kS = this.A.getVirtualMemory();
         this.wS = this.A.getGlobalContext();
         this.UT = this.wS.getNativeContext().getProcessor().getType();
         this.E = var1.E.getGPRegisterBitsize() == 64;
         this.sY = new RoutineIOHandler(this.A);
      }
   }

   @Override
   public Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      switch (ckx.kS(var2)) {
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
            this.pC(var2);
            return true;
         default:
            return null;
      }
   }

   void pC(String var1) throws cej {
      this.sY.reset(this.pC.UT);
      if (this.pC.Er != 0) {
         switch (ckx.kS(var1)) {
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
               throw new cej(
                  ckx.pC(
                        new byte[]{
                           22, 1, 24, 24, 28, 13, 11, 13, 16, 0, 77, 27, 74, 69, 65, 77, 80, 92, 66, 0, 91, 94, 18, 127, 98, 105, 99, 82, 19, 6, 10, 12, 68, 92
                        },
                        2,
                        126
                     )
                     + this.pC.Er
               );
         }
      }

      switch (ckx.kS(var1)) {
         case -2146792506:
            this.pP();
            break;
         case -2134950756:
            this.KK();
            break;
         case -2120936779:
            this.Gm();
            break;
         case -2097579900:
            this.vv();
            break;
         case -2051890678:
            this.rC();
            break;
         case -2051688172:
            this.l();
            break;
         case -2016526083:
            this.Gg();
            break;
         case -2012118445:
            this.pg();
            break;
         case -1982715289:
            this.x();
            break;
         case -1948659323:
            this.gy();
            break;
         case -1948333157:
            this.Br();
            break;
         case -1943483988:
            this.wj();
            break;
         case -1943211781:
            this.TD();
            break;
         case -1931363395:
            this.gR();
            break;
         case -1924218098:
            this.mz();
            break;
         case -1916688867:
            this.rB();
            break;
         case -1914909024:
            this.tH();
            break;
         case -1878685356:
            this.mV();
            break;
         case -1838906575:
            this.wQ();
            break;
         case -1826521030:
            this.FE();
            break;
         case -1819897800:
            this.ii();
            break;
         case -1808452158:
            this.MJ();
            break;
         case -1799386836:
            this.hq();
            break;
         case -1789605352:
            this.OI();
            break;
         case -1785426108:
            this.JP();
            break;
         case -1784231296:
            this.zJ();
            break;
         case -1781666358:
            this.dF();
            break;
         case -1762186721:
            this.pF();
            break;
         case -1733680079:
            this.UT();
            break;
         case -1714194354:
            this.z();
            break;
         case -1688064942:
            this.Bc();
            break;
         case -1678869756:
            this.Eq();
            break;
         case -1674784009:
            this.WR();
            break;
         case -1649412263:
            this.Jq();
            break;
         case -1615592845:
            this.KV();
            break;
         case -1591999000:
            this.ah();
            break;
         case -1577428797:
            this.A();
            break;
         case -1569093027:
            this.Rh();
            break;
         case -1498942684:
            this.Um();
            break;
         case -1492562733:
            this.lt();
            break;
         case -1490535854:
            this.Sc();
            break;
         case -1462753928:
            this.Tr();
            break;
         case -1439307331:
            this.kT();
            break;
         case -1414923680:
            this.os();
            break;
         case -1394772922:
            this.ee();
            break;
         case -1364750509:
            this.Is();
            break;
         case -1336500526:
            this.kB();
            break;
         case -1328586975:
            this.KW();
            break;
         case -1306977625:
            this.ZY();
            break;
         case -1255184679:
            this.fH();
            break;
         case -1246288620:
            this.RW();
            break;
         case -1245617332:
            this.HG();
            break;
         case -1236449472:
            this.Op();
            break;
         case -1223888971:
            this.LV();
            break;
         case -1223314468:
            this.TP();
            break;
         case -1222903032:
            this.oc();
            break;
         case -1204806184:
            this.yB();
            break;
         case -1200119012:
            this.uD();
            break;
         case -1185334531:
            this.ND();
            break;
         case -1135228531:
            this.So();
            break;
         case -1130056856:
            this.gW();
            break;
         case -1095953659:
            this.mK();
            break;
         case -1095472940:
            this.fI();
            break;
         case -1090370219:
            this.JF();
            break;
         case -1084054661:
            this.JV();
            break;
         case -1051831395:
            this.lZ();
            break;
         case -1022466774:
            this.iX();
            break;
         case -968098752:
            this.rI();
            break;
         case -965785503:
            this.mv();
            break;
         case -945387788:
            this.PR();
            break;
         case -932617484:
            this.gj();
            break;
         case -921253872:
            this.Ek();
            break;
         case -911549441:
            this.cH();
            break;
         case -888122860:
            this.UJ();
            break;
         case -873740904:
            this.jj();
            break;
         case -867914684:
            this.Mi();
            break;
         case -864523024:
            this.hM();
            break;
         case -852353139:
            this.NS();
            break;
         case -837251673:
            this.NB();
            break;
         case -833102966:
            this.RR();
            break;
         case -832586445:
            this.hB();
            break;
         case -809066121:
            this.yi();
            break;
         case -779904145:
            this.Xs();
            break;
         case -769792372:
            this.vP();
            break;
         case -750297513:
            this.eI();
            break;
         case -727180593:
            this.TV();
            break;
         case -715027112:
            this.kQ();
            break;
         case -711410735:
            this.Fx();
            break;
         case -708564284:
            this.bm();
            break;
         case -707370984:
            this.MZ();
            break;
         case -702155279:
            this.Bi();
            break;
         case -701651984:
            this.LM();
            break;
         case -699995339:
            this.rl();
            break;
         case -677503563:
            this.hZ();
            break;
         case -661477963:
            this.Gh();
            break;
         case -657315539:
            this.aU();
            break;
         case -652845793:
            this.pt();
            break;
         case -623458268:
            this.IQ();
            break;
         case -604053979:
            this.PZ();
            break;
         case -593476110:
            this.eP();
            break;
         case -581214837:
            this.VE();
            break;
         case -579618570:
            this.ys();
            break;
         case -565300011:
            this.xy();
            break;
         case -555619603:
            this.e();
            break;
         case -512522823:
            this.uE();
            break;
         case -493824257:
            this.AS();
            break;
         case -490302269:
            this.ll();
            break;
         case -466470545:
            this.LA();
            break;
         case -428425146:
            this.B();
            break;
         case -415116730:
            this.sO();
            break;
         case -408797597:
            this.ib();
            break;
         case -395427250:
            this.Kq();
            break;
         case -390949721:
            this.ho();
            break;
         case -388730666:
            this.yC();
            break;
         case -386390433:
            this.Bf();
            break;
         case -385424675:
            this.DL();
            break;
         case -335759550:
            this.UO();
            break;
         case -318852672:
            this.wF();
            break;
         case -252051364:
            this.fD();
            break;
         case -246422608:
            this.sz();
            break;
         case -206865257:
            this.Fz();
            break;
         case -199909539:
            this.KC();
            break;
         case -199578785:
            this.jS();
            break;
         case -144535406:
            this.uu();
            break;
         case -112413852:
            this.Wm();
            break;
         case -79866638:
            this.y();
            break;
         case -34985879:
            this.yv();
            break;
         case -29874824:
            this.Ck();
            break;
         case -16075565:
            this.Tq();
            break;
         case -4669976:
            this.kS();
            break;
         case 31040006:
            this.ZN();
            break;
         case 34719615:
            this.OB();
            break;
         case 42261176:
            this.Ft();
            break;
         case 92008111:
            this.ck();
            break;
         case 99207718:
            this.WX();
            break;
         case 128747975:
            this.DM();
            break;
         case 135434879:
            this.pY();
            break;
         case 140170388:
            this.Ab();
            break;
         case 144110970:
            this.xC();
            break;
         case 148708192:
            this.tR();
            break;
         case 159656454:
            this.FK();
            break;
         case 167236625:
            this.qG();
            break;
         case 171085380:
            this.kk();
            break;
         case 186191386:
            this.ZD();
            break;
         case 193096395:
            this.jH();
            break;
         case 200650863:
            this.vU();
            break;
         case 250945779:
            this.te();
            break;
         case 261910796:
            this.xg();
            break;
         case 269185143:
            this.fN();
            break;
         case 295494544:
            this.sY();
            break;
         case 336264978:
            this.vr();
            break;
         case 364608423:
            this.FM();
            break;
         case 388176200:
            this.hw();
            break;
         case 389891295:
            this.Er();
            break;
         case 401317503:
            this.hK();
            break;
         case 411443080:
            this.aK();
            break;
         case 421357621:
            this.BP();
            break;
         case 431251782:
            this.E();
            break;
         case 476786442:
            this.uw();
            break;
         case 552183643:
            this.kt();
            break;
         case 561293667:
            this.Aj();
            break;
         case 577170125:
            this.Rq();
            break;
         case 633268445:
            this.IK();
            break;
         case 677031288:
            this.xM();
            break;
         case 712897499:
            this.Fm();
            break;
         case 722129746:
            this.Ig();
            break;
         case 737063522:
            this.pC();
            break;
         case 740969735:
            this.eE();
            break;
         case 761982294:
            this.DQ();
            break;
         case 810002497:
            this.sR();
            break;
         case 816092528:
            this.CK();
            break;
         case 828670056:
            this.Iq();
            break;
         case 846090276:
            this.BX();
            break;
         case 847952879:
            this.Xh();
            break;
         case 856237346:
            this.dC();
            break;
         case 878749552:
            this.wS();
            break;
         case 892394213:
            this.kU();
            break;
         case 979980119:
            this.Yw();
            break;
         case 985139829:
            this.ld();
            break;
         case 1004043698:
            this.UW();
            break;
         case 1018439764:
            this.Gu();
            break;
         case 1021601417:
            this.lO();
            break;
         case 1029630465:
            this.Nq();
            break;
         case 1105816151:
            this.AQ();
            break;
         case 1116709725:
            this.UH();
            break;
         case 1125005996:
            this.sd();
            break;
         case 1158014871:
            this.ED();
            break;
         case 1172415263:
            this.zL();
            break;
         case 1206140885:
            this.IE();
            break;
         case 1256698242:
            this.gp();
            break;
         case 1311320894:
            this.jY();
            break;
         case 1360784728:
            this.uJ();
            break;
         case 1376556447:
            this.QQ();
            break;
         case 1432154034:
            this.EM();
            break;
         case 1465427985:
            this.Ip();
            break;
         case 1474401868:
            this.Wn();
            break;
         case 1477023054:
            this.VD();
            break;
         case 1488856767:
            this.Yi();
            break;
         case 1495610894:
            this.Cu();
            break;
         case 1497714135:
            this.FA();
            break;
         case 1498491051:
            this.np();
            break;
         case 1564462299:
            this.mr();
            break;
         case 1567541837:
            this.OD();
            break;
         case 1586414828:
            this.LL();
            break;
         case 1600322159:
            this.dM();
            break;
         case 1619804715:
            this.OA();
            break;
         case 1648243687:
            this.dE();
            break;
         case 1650328425:
            this.zK();
            break;
         case 1667025175:
            this.oB();
            break;
         case 1681513940:
            this.pW();
            break;
         case 1684260316:
            this.oT();
            break;
         case 1697043521:
            this.ET();
            break;
         case 1713573578:
            this.HO();
            break;
         case 1739278613:
            this.hE();
            break;
         case 1774565545:
            this.AU();
            break;
         case 1782232408:
            this.NN();
            break;
         case 1793833288:
            this.un();
            break;
         case 1799892967:
            this.hF();
            break;
         case 1829605578:
            this.cX();
            break;
         case 1836847084:
            this.KM();
            break;
         case 1856424862:
            this.ve();
            break;
         case 1869495233:
            this.QP();
            break;
         case 1886159338:
            this.fn();
            break;
         case 1887682946:
            this.zR();
            break;
         case 1911845198:
            this.ct();
            break;
         case 1950595473:
            this.Ta();
            break;
         case 1976222283:
            this.Pe();
            break;
         case 1994000148:
            this.go();
            break;
         case 1994699200:
            this.be();
            break;
         case 2022152394:
            this.ik();
            break;
         case 2027859044:
            this.EX();
            break;
         case 2108697204:
            this.gd();
            break;
         case 2114197384:
            this.XZ();
            break;
         case 2141110471:
            this.dW();
            break;
         default:
            throw new RuntimeException(
               ckx.pC(new byte[]{-39, 22, 11, 115, 26, 77, 4, 26, 0, 26, 7, 9, 71, 78, 11, 18, 87, 74, 4, 7, 73, 82, 29, 26, 1, 29, 7, 11, 95, 26}, 1, 141)
                  + var1
            );
      }

      this.A.processStoredReturnAddress(this.pC.UT.getReturnAddressSlot());
   }

   private void pC() {
      this.sY.skipJniEnvPtr();
      this.sY.retInt(-1);
   }

   private void A() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      this.sY.nextAsPtr();
      this.A.writePointer(var1, this.pC.Ek);
      this.sY.retInt(0);
   }

   private void kS() {
      this.sY.skipJniEnvPtr();
      this.sY.retInt(0);
   }

   private void wS() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      int var3 = this.sY.nextAsInt();
      long var4;
      byte var6;
      if (var3 < 65537) {
         var4 = 0L;
         var6 = -3;
      } else {
         var4 = this.pC.Ek;
         var6 = 0;
      }

      this.A.writePointer(var1, var4);
      this.sY.retInt(var6);
   }

   private void UT() {
      this.A();
   }

   private void E() {
      this.sY.skipJniEnvPtr();
      this.sY.retInt(65542);
   }

   private void sY() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsPtr();
      this.sY.nextAsObj();
      this.sY.nextAsPtr();
      this.sY.nextAsInt();
      this.sY.retObj(0);
   }

   private void ys() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      String var3 = cek.pC(this.kS, var1);
      String var4 = cek.A(var3);
      Object[] var10000 = new Object[]{var4};
      int var5 = 0;

      try {
         var5 = this.pC.pC(var4);
      } catch (DexDecEvalCodeThrownException var8) {
         this.pC.Er = var8.getThrownObjectRef().getObjectReferenceId();
      } catch (DexDecEvaluationException var9) {
         if (!(var9.getCause() instanceof ClassNotFoundException)) {
            throw new cej(
               ckx.pC(
                     new byte[]{-126, 34, 15, 0, 1, 27, 84, 82, 23, 17, 6, 27, 12, 19, 19, 69, 69, 8, 24, 25, 13, 21, 17, 1, 68, 67, 15, 13, 18, 0, 83}, 1, 193
                  )
                  + var4,
               var9
            );
         }

         IDImm var7 = this.pC.ED.registerObject(var9.getCause());
         this.pC.Er = var7.getObjectReferenceId();
      }

      this.sY.retInt(var5);
   }

   private void ld() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      this.sY.retInt(0);
   }

   private void gp() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();
      this.sY.nextAsInt();
      String var3 = this.pC.E(var2);
      JvmMethodSig var4 = JvmMethodSig.parse(var3);

      try {
         IDImm[] var5 = new IDImm[3];
         String var6 = this.pC.wS(var1);
         var5[0] = this.pC.ED.getClassReference(var6);
         var5[1] = this.pC.ED.registerObject(var4.mname);
         ArrayList var7 = new ArrayList(var4.partypes.size());

         for (String var9 : var4.partypes) {
            var7.add(this.pC.ED.getClassReference(var9));
         }

         var5[2] = this.pC.ED.createArray("[Ljava/lang/Class;", var4.partypes.size(), var7);
         IDImm var11 = this.pC
            .ED
            .invokeMethod(DInvokeType.VIRTUAL, "Ljava/lang/Class;->getDeclaredMethod(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", var5);
         this.sY.retObj(var11.getObjectReferenceId());
      } catch (DexDecEvaluationException var10) {
         this.sY.retObj(0);
      }
   }

   private void oT() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      this.sY.retInt(0);
   }

   private void fI() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();
      this.sY.nextAsInt();
      String var3 = this.pC.UT(var2);
      JvmFieldSig var4 = JvmFieldSig.parse(var3);

      try {
         IDImm[] var5 = new IDImm[2];
         String var6 = this.pC.wS(var1);
         var5[0] = this.pC.ED.getClassReference(var6);
         var5[1] = this.pC.ED.registerObject(var4.fname);
         IDImm var7 = this.pC.ED.invokeMethod(DInvokeType.VIRTUAL, "Ljava/lang/Class;->getDeclaredField(Ljava/lang/String;)Ljava/lang/reflect/Field;", var5);
         this.sY.retObj(var7.getObjectReferenceId());
      } catch (DexDecEvaluationException var8) {
         this.sY.retObj(0);
      }
   }

   private void WR() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      String var3 = this.pC.wS(var1);
      int var2;
      if (var3.equals("Ljava/lang/Object;")) {
         var2 = 0;
      } else {
         IJLSTypeAdapter var4 = this.pC.ED.getTypeAdapter();
         boolean var5 = (var4.getType(var3).getAccessFlags() & 512) != 0;
         if (var5) {
            var2 = 0;
         } else {
            String var6 = var4.getSupertype(var3);

            try {
               var2 = this.pC.pC(var6);
            } catch (DexDecEvaluationException var8) {
               throw new cej(
                  ckx.pC(
                        new byte[]{0, 14, 30, 23, 29, 29, 71, 26, 17, 84, 90, 10, 76, 86, 84, 25, 92, 94, 89, 76, 83, 68, 87, 81, 12, 67, 35, 19, 18, 16, 76},
                        2,
                        180
                     )
                     + var3,
                  var8
               );
            }
         }
      }

      this.sY.retObj(var2);
   }

   private void NS() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsObj();
      String var3 = this.pC.wS(var1);
      String var4 = this.pC.wS(var2);
      IJLSTypeAdapter var5 = this.pC.ED.getTypeAdapter();
      int var6 = bgq.pC(var5, var3, var4) ? 1 : 0;
      this.sY.retInt(var6);
   }

   private void vP() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      byte var2;
      if (var1 == 0) {
         var2 = -1;
      } else {
         this.pC.Er = var1;
         var2 = 0;
      }

      this.sY.retInt(var2);
   }

   private void xC() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      String var4 = this.pC.wS(var1);
      String var5 = cek.pC(this.kS, var2);
      this.pC.ED.getGlobalContext();

      byte var6;
      try {
         IDImm var7 = this.pC.ED.registerObject(var5);
         int var8 = this.pC.ED.createNewInstance(var4 + "-><init>(Ljava/lang/String;)V", Arrays.asList(var7)).getObjectReferenceId();
         this.pC.Er = var8;
         var6 = 0;
      } catch (DexDecEvaluationException var9) {
         var6 = -1;
      }

      this.sY.retInt(var6);
   }

   private void ED() {
      this.sY.skipJniEnvPtr();
      int var1 = 0;
      if (this.pC.Er != 0) {
         var1 = this.pC.Er;
      }

      this.sY.retObj(var1);
   }

   private void Sc() {
      this.sY.skipJniEnvPtr();
   }

   private void ah() {
      this.sY.skipJniEnvPtr();
      this.pC.Er = 0;
   }

   private void eP() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      String var3 = cek.pC(this.kS, var1);
      throw new cej("Fatal error: " + var3);
   }

   private void UO() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsInt();
      this.sY.retInt(0);
   }

   private void Ab() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      this.sY.retObj(var1);
   }

   private void rl() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      this.sY.retObj(var1);
   }

   private void z() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
   }

   private void Ek() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
   }

   private void hK() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsObj();
      int var3 = var1 == var2 ? 1 : 0;
      this.sY.retInt(var3);
   }

   private void Er() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      this.sY.retObj(var1);
   }

   private void FE() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsInt();
      this.sY.retInt(0);
   }

   private void Aj() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      this.sY.retObj(0);
   }

   private void EX() {
      this.pC("L", 3, 0);
   }

   private void LM() {
      this.pC("L", 3, 1);
   }

   private void mv() {
      this.pC("L", 3, 2);
   }

   private void sO() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsInt();

      int var2;
      try {
         var2 = this.pC.ED.getObjectClassId(var1);
      } catch (DexDecEvaluationException var4) {
         throw new cej(var4);
      }

      this.sY.retInt(var2);
   }

   private void os() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsObj();

      int var3;
      try {
         var3 = this.pC.ED.getObjectClassId(var1);
      } catch (DexDecEvaluationException var8) {
         throw new cej(var8);
      }

      String var4 = this.pC.wS(var3);
      String var5 = this.pC.wS(var2);
      IJLSTypeAdapter var6 = this.pC.ED.getTypeAdapter();
      int var7 = bgq.pC(var6, var4, var5) ? 1 : 0;
      this.sY.retInt(var7);
   }

   private void Cu() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      long var4 = this.sY.nextAsPtr();
      String var6 = cek.pC(this.kS, var2);
      String var7 = cek.pC(this.kS, var4);
      String var8 = this.pC.wS(var1);
      String var9 = var8 + "->" + var6 + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.pC.kS(var9);
      this.sY.retObj(var10);
   }

   private void pC(String var1, int var2, int var3) {
      this.sY.skipJniEnvPtr();
      int var5 = 0;
      DInvokeType var4;
      if (var2 == 0) {
         var4 = DInvokeType.VIRTUAL;
         var5 = this.sY.nextAsObj();
      } else if (var2 == 1) {
         var4 = DInvokeType.DIRECT;
         var5 = this.sY.nextAsObj();
         this.sY.nextAsObj();
      } else if (var2 == 2) {
         var4 = DInvokeType.STATIC;
         this.sY.nextAsObj();
      } else {
         if (var2 != 3) {
            throw new RuntimeException();
         }

         var4 = DInvokeType.NEW;
         this.sY.nextAsObj();
      }

      int var6 = this.sY.nextAsObj();
      String var7 = this.pC.E(var6);
      JvmMethodSig var8 = JvmMethodSig.parse(var7);
      IDGlobalContext var9 = this.pC.ED.getGlobalContext();
      ArrayList var10 = new ArrayList();
      if (var2 == 0 || var2 == 1) {
         var10.add(var9.createRef(var5));
      }

      if (var3 == 0) {
         for (String var12 : var8.partypes) {
            var10.add(switch (var12.charAt(0)) {
               case 'B', 'C', 'I', 'S', 'Z' -> var9.createImm(this.sY.nextAsInt(), var9.getTypeFactory().createType(var12));
               case 'D' -> var9.createDouble(this.sY.nextAsDouble());
               default -> throw new RuntimeException();
               case 'F' -> {
                  float var42;
                  if (this.E) {
                     var42 = (float)this.sY.nextAsDouble();
                  } else {
                     var42 = this.sY.nextAsFloat();
                  }

                  yield var9.createFloat(var42);
               }
               case 'J' -> var9.createLong(this.sY.nextAsLong());
               case 'L', '[' -> var9.createRef(this.sY.nextAsObj());
            });
         }
      } else if (var3 != 1) {
         if (var3 != 2) {
            throw new RuntimeException();
         }

         long var27 = this.sY.nextAsPtr();
         INativeType var37 = this.pC.wS.getType("jvalue");
         VMReader var41 = new VMReader(this.kS);

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
         long var11 = this.sY.nextAsPtr();

         for (String var14 : var8.partypes) {
            if (var14.equals("F") || var14.equals("D")) {
               throw new RuntimeException();
            }
         }

         ArrayList var36 = new ArrayList();
         INativeType var40 = this.pC.wS.getType("jvalue");
         VMReader var15 = new VMReader(this.kS);
         if (this.UT == ProcessorType.X86_64) {
            throw new RuntimeException();
         }

         if (this.UT != ProcessorType.ARM64) {
            throw new RuntimeException();
         }

         long var16;
         try {
            long var18 = this.kS.readPointer(var11 + 8L);
            int var20 = this.kS.readInt(var11 + 24L);
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
            var29 = this.pC.ED.createNewInstance(var7, var10);
         } else {
            var29 = this.pC.ED.invokeMethod(var7, var10, var4);
         }
      } catch (DexDecEvalCodeThrownException var24) {
         int var39 = var24.getThrownObjectRef().getObjectReferenceId();
         this.pC.Er = var39;
         return;
      } catch (DexDecEvaluationException var25) {
         throw new cej(var25);
      }

      if (var4 == DInvokeType.NEW) {
         int var35 = (int)var29.getRawValue();
         this.sY.retObj(var35);
      } else {
         switch (var8.rettype.charAt(0)) {
            case 'B':
            case 'C':
            case 'I':
            case 'S':
            case 'Z':
               int var34 = (int)var29.getRawValue();
               this.sY.retInt(var34);
               break;
            case 'D':
               double var33 = Double.longBitsToDouble(var29.getRawValue());
               this.sY.retDouble(var33);
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
               this.sY.retObj(var32);
               break;
            case 'F':
               float var31 = Float.intBitsToFloat((int)var29.getRawValue());
               this.sY.retFloat(var31);
               break;
            case 'J':
               long var30 = var29.getRawValue();
               this.sY.retLong(var30);
            case 'V':
         }
      }
   }

   private void hZ() {
      this.pC("L", 0, 0);
   }

   private void UW() {
      this.pC("L", 0, 1);
   }

   private void PR() {
      this.pC("L", 0, 2);
   }

   private void cX() {
      this.pC("Z", 0, 0);
   }

   private void DQ() {
      this.pC("Z", 0, 1);
   }

   private void ZN() {
      this.pC("Z", 0, 2);
   }

   private void OB() {
      this.pC("B", 0, 0);
   }

   private void pF() {
      this.pC("B", 0, 1);
   }

   private void Bc() {
      this.pC("B", 0, 2);
   }

   private void OI() {
      this.pC("C", 0, 0);
   }

   private void Bf() {
      this.pC("C", 0, 1);
   }

   private void Pe() {
      this.pC("C", 0, 2);
   }

   private void ck() {
      this.pC("S", 0, 0);
   }

   private void RW() {
      this.pC("S", 0, 1);
   }

   private void e() {
      this.pC("S", 0, 2);
   }

   private void xM() {
      this.pC("I", 0, 0);
   }

   private void kU() {
      this.pC("I", 0, 1);
   }

   private void Kq() {
      this.pC("I", 0, 2);
   }

   private void go() {
      this.pC("J", 0, 0);
   }

   private void JF() {
      this.pC("J", 0, 1);
   }

   private void Nq() {
      this.pC("J", 0, 2);
   }

   private void pg() {
      this.pC("F", 0, 0);
   }

   private void gj() {
      this.pC("F", 0, 1);
   }

   private void ZD() {
      this.pC("F", 0, 2);
   }

   private void DL() {
      this.pC("D", 0, 0);
   }

   private void UH() {
      this.pC("D", 0, 1);
   }

   private void VD() {
      this.pC("D", 0, 2);
   }

   private void Xs() {
      this.pC("V", 0, 0);
   }

   private void KV() {
      this.pC("V", 0, 1);
   }

   private void FK() {
      this.pC("V", 0, 2);
   }

   private void Bi() {
      this.pC("L", 1, 0);
   }

   private void wQ() {
      this.pC("L", 1, 1);
   }

   private void PZ() {
      this.pC("L", 1, 2);
   }

   private void Ip() {
      this.pC("Z", 1, 0);
   }

   private void Fm() {
      this.pC("Z", 1, 1);
   }

   private void FM() {
      this.pC("Z", 1, 2);
   }

   private void Wn() {
      this.pC("B", 1, 0);
   }

   private void gy() {
      this.pC("B", 1, 1);
   }

   private void pt() {
      this.pC("B", 1, 2);
   }

   private void uE() {
      this.pC("C", 1, 0);
   }

   private void Um() {
      this.pC("C", 1, 1);
   }

   private void Ta() {
      this.pC("C", 1, 2);
   }

   private void So() {
      this.pC("S", 1, 0);
   }

   private void tH() {
      this.pC("S", 1, 1);
   }

   private void Gm() {
      this.pC("S", 1, 2);
   }

   private void Br() {
      this.pC("I", 1, 0);
   }

   private void IE() {
      this.pC("I", 1, 1);
   }

   private void AU() {
      this.pC("I", 1, 2);
   }

   private void jS() {
      this.pC("J", 1, 0);
   }

   private void KK() {
      this.pC("J", 1, 1);
   }

   private void oB() {
      this.pC("J", 1, 2);
   }

   private void Rq() {
      this.pC("F", 1, 0);
   }

   private void LL() {
      this.pC("F", 1, 1);
   }

   private void rC() {
      this.pC("F", 1, 2);
   }

   private void be() {
      this.pC("D", 1, 0);
   }

   private void Xh() {
      this.pC("D", 1, 1);
   }

   private void sz() {
      this.pC("D", 1, 2);
   }

   private void QQ() {
      this.pC("V", 1, 0);
   }

   private void eE() {
      this.pC("V", 1, 1);
   }

   private void dM() {
      this.pC("V", 1, 2);
   }

   private void EM() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      long var4 = this.sY.nextAsPtr();
      String var6 = cek.pC(this.kS, var2);
      String var7 = cek.pC(this.kS, var4);
      String var8 = this.pC.wS(var1);
      String var9 = var8 + "->" + var6 + ":" + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.pC.A(var9);
      this.sY.retObj(var10);
   }

   private void pC(char var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      int var3 = this.sY.nextAsObj();
      String var4 = this.pC.UT(var3);
      Object[] var10000 = new Object[]{var4};

      long var5;
      try {
         IDGlobalContext var7 = this.pC.ED.getGlobalContext();
         IDImm var8 = this.pC.ED.getInstanceField(var4, var7.createRef(var2));
         var5 = var8.getRawValue();
      } catch (DexDecEvaluationException var9) {
         throw new cej(var9);
      }

      switch (var1) {
         case 'B':
            this.sY.retInt((byte)var5);
            break;
         case 'C':
            this.sY.retInt((char)var5);
            break;
         case 'D':
            this.sY.retDouble(Double.longBitsToDouble(var5));
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
            this.sY.retFloat(Float.intBitsToFloat((int)var5));
            break;
         case 'I':
            this.sY.retInt((int)var5);
            break;
         case 'J':
            this.sY.retLong(var5);
            break;
         case 'L':
            this.sY.retObj((int)var5);
            break;
         case 'S':
            this.sY.retInt((short)var5);
            break;
         case 'Z':
            this.sY.retInt((int)var5 & 1);
      }
   }

   private void fD() {
      this.pC('L');
   }

   private void ii() {
      this.pC('Z');
   }

   private void Gu() {
      this.pC('B');
   }

   private void hw() {
      this.pC('C');
   }

   private void qG() {
      this.pC('S');
   }

   private void yi() {
      this.pC('I');
   }

   private void zK() {
      this.pC('J');
   }

   private void LA() {
      this.pC('F');
   }

   private void ve() {
      this.pC('D');
   }

   private void A(char var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      int var3 = this.sY.nextAsObj();

      long var4 = switch (var1) {
         case 'B' -> (byte)this.sY.nextAsInt();
         case 'C' -> this.sY.nextAsInt() & 65535L;
         case 'D' -> Double.doubleToLongBits(this.sY.nextAsDouble());
         default -> throw new RuntimeException();
         case 'F' -> Float.floatToIntBits(this.sY.nextAsFloat());
         case 'I' -> this.sY.nextAsInt();
         case 'J' -> this.sY.nextAsLong();
         case 'L' -> this.sY.nextAsObj();
         case 'S' -> (short)this.sY.nextAsInt();
         case 'Z' -> this.sY.nextAsInt() & 1;
      };
      String var6 = this.pC.UT(var3);
      Object[] var10000 = new Object[]{var6};

      try {
         IDGlobalContext var7 = this.pC.ED.getGlobalContext();
         IDImm var8 = var7.createImm(var4, var7.getTypeFactory().letterToType(var1));
         this.pC.ED.setInstanceField(var6, var7.createRef(var2), var8);
      } catch (DexDecEvaluationException var9) {
         throw new cej(var9);
      }
   }

   private void yv() {
      this.A('L');
   }

   private void MZ() {
      this.A('Z');
   }

   private void fH() {
      this.A('B');
   }

   private void ET() {
      this.A('C');
   }

   private void kk() {
      this.A('S');
   }

   private void Rh() {
      this.A('I');
   }

   private void vv() {
      this.A('J');
   }

   private void fn() {
      this.A('F');
   }

   private void AS() {
      this.A('D');
   }

   private void wF() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      long var4 = this.sY.nextAsPtr();
      String var6 = cek.pC(this.kS, var2);
      String var7 = cek.pC(this.kS, var4);
      String var8 = this.pC.wS(var1);
      String var9 = var8 + "->" + var6 + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.pC.kS(var9);
      this.sY.retObj(var10);
   }

   private void hF() {
      this.pC("L", 2, 0);
   }

   private void FA() {
      this.pC("L", 2, 1);
   }

   private void IK() {
      this.pC("L", 2, 2);
   }

   private void DM() {
      this.pC("Z", 2, 0);
   }

   private void IQ() {
      this.pC("Z", 2, 1);
   }

   private void zR() {
      this.pC("Z", 2, 2);
   }

   private void Ft() {
      this.pC("B", 2, 0);
   }

   private void kt() {
      this.pC("B", 2, 1);
   }

   private void Yw() {
      this.pC("B", 2, 2);
   }

   private void uD() {
      this.pC("C", 2, 0);
   }

   private void ZY() {
      this.pC("C", 2, 1);
   }

   private void mK() {
      this.pC("C", 2, 2);
   }

   private void pW() {
      this.pC("S", 2, 0);
   }

   private void Gg() {
      this.pC("S", 2, 1);
   }

   private void kQ() {
      this.pC("S", 2, 2);
   }

   private void te() {
      this.pC("I", 2, 0);
   }

   private void B() {
      this.pC("I", 2, 1);
   }

   private void RR() {
      this.pC("I", 2, 2);
   }

   private void CK() {
      this.pC("J", 2, 0);
   }

   private void Eq() {
      this.pC("J", 2, 1);
   }

   private void y() {
      this.pC("J", 2, 2);
   }

   private void JP() {
      this.pC("F", 2, 0);
   }

   private void jY() {
      this.pC("F", 2, 1);
   }

   private void ee() {
      this.pC("F", 2, 2);
   }

   private void ho() {
      this.pC("D", 2, 0);
   }

   private void VE() {
      this.pC("D", 2, 1);
   }

   private void lt() {
      this.pC("D", 2, 2);
   }

   private void uw() {
      this.pC("V", 2, 0);
   }

   private void QP() {
      this.pC("V", 2, 1);
   }

   private void hM() {
      this.pC("V", 2, 2);
   }

   private void MJ() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      long var4 = this.sY.nextAsPtr();
      String var6 = cek.pC(this.kS, var2);
      String var7 = cek.pC(this.kS, var4);
      String var8 = this.pC.wS(var1);
      String var9 = var8 + "->" + var6 + ":" + var7;
      Object[] var10000 = new Object[]{var9};
      int var10 = this.pC.A(var9);
      this.sY.retObj(var10);
   }

   private void kS(char var1) {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      int var2 = this.sY.nextAsObj();
      String var3 = this.pC.UT(var2);
      Object[] var10000 = new Object[]{var3};

      long var4;
      try {
         IDImm var6 = this.pC.ED.getStaticField(var3);
         var4 = var6.getRawValue();
      } catch (DexDecEvaluationException var7) {
         throw new cej(var7);
      }

      switch (var1) {
         case 'B':
            this.sY.retInt((byte)var4);
            break;
         case 'C':
            this.sY.retInt((char)var4);
            break;
         case 'D':
            this.sY.retDouble(Double.longBitsToDouble(var4));
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
            this.sY.retFloat(Float.intBitsToFloat((int)var4));
            break;
         case 'I':
            this.sY.retInt((int)var4);
            break;
         case 'J':
            this.sY.retLong(var4);
            break;
         case 'L':
            this.sY.retObj((int)var4);
            break;
         case 'S':
            this.sY.retInt((short)var4);
            break;
         case 'Z':
            this.sY.retInt((int)var4 & 1);
      }
   }

   private void OA() {
      this.kS('L');
   }

   private void kT() {
      this.kS('Z');
   }

   private void x() {
      this.kS('B');
   }

   private void un() {
      this.kS('C');
   }

   private void JV() {
      this.kS('S');
   }

   private void Iq() {
      this.kS('I');
   }

   private void mV() {
      this.kS('J');
   }

   private void Gh() {
      this.kS('F');
   }

   private void HG() {
      this.kS('D');
   }

   private void wS(char var1) {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      int var2 = this.sY.nextAsObj();

      long var3 = switch (var1) {
         case 'B' -> (byte)this.sY.nextAsInt();
         case 'C' -> this.sY.nextAsInt() & 65535L;
         case 'D' -> Double.doubleToLongBits(this.sY.nextAsDouble());
         default -> throw new RuntimeException();
         case 'F' -> Float.floatToIntBits(this.sY.nextAsFloat());
         case 'I' -> this.sY.nextAsInt();
         case 'J' -> this.sY.nextAsLong();
         case 'L' -> this.sY.nextAsObj();
         case 'S' -> (short)this.sY.nextAsInt();
         case 'Z' -> this.sY.nextAsInt() & 1;
      };
      String var5 = this.pC.UT(var2);
      Object[] var10000 = new Object[]{var5};

      try {
         IDGlobalContext var6 = this.pC.ED.getGlobalContext();
         IDImm var7 = var6.createImm(var3, var6.getTypeFactory().letterToType(var1));
         this.pC.ED.setStaticField(var5, var7);
      } catch (DexDecEvaluationException var8) {
         throw new cej(var8);
      }
   }

   private void yC() {
      this.wS('L');
   }

   private void uu() {
      this.wS('Z');
   }

   private void Tq() {
      this.wS('B');
   }

   private void HO() {
      this.wS('C');
   }

   private void Is() {
      this.wS('S');
   }

   private void BP() {
      this.wS('I');
   }

   private void Wm() {
      this.wS('J');
   }

   private void TP() {
      this.wS('F');
   }

   private void gd() {
      this.wS('D');
   }

   private void eI() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      int var3 = this.sY.nextAsInt();
      int var4 = var3 * 2;
      byte[] var5 = new byte[var4];
      VirtualMemoryUtil.readBytesSafe(this.kS, var1, var5.length, var5, 0, true);
      char[] var6 = new char[var3];
      int var7 = 0;

      for (byte var8 = 0; var8 < var4; var8 += 2) {
         var6[var7] = (char)(var5[var8] & 255 | var5[var8 + 1] << 8 & 0xFF00);
         var7++;
      }

      String var10 = new String(var6);
      Object[] var10000 = new Object[]{Formatter.escapeString(var10, false)};
      int var9 = this.pC.ED.registerObject(var10).getObjectReferenceId();
      this.sY.retObj(var9);
   }

   private void lZ() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();

      int var2;
      try {
         String var3 = (String)this.pC.ED.getObject(var1);
         var2 = var3.length();
      } catch (DexDecEvaluationException var4) {
         throw new cej(var4);
      }

      this.sY.retInt(var2);
   }

   private void AQ() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      if (var2 != 0L) {
         try {
            this.kS.writeInt(var2, 1);
         } catch (MemoryException var9) {
            throw new cej(var9);
         }
      }

      long var4;
      try {
         String var6 = (String)this.pC.ED.getObject(var1);
         char[] var7 = var6.toCharArray();
         var4 = this.A.heapAlloc(var7.length * 2);
         VirtualMemoryUtil.writeChars(this.kS, var4, var7, 0, var7.length);
      } catch (DexDecEvaluationException var8) {
         throw new cej(var8);
      }

      this.sY.retPtr(var4);
   }

   private void BX() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      long var1 = this.sY.nextAsPtr();
      this.A.heapFree(var1);
   }

   private void xg() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      String var3 = cek.pC(this.kS, var1);
      Object[] var10000 = new Object[]{var3};
      int var4 = this.pC.ED.registerObject(var3).getObjectReferenceId();
      this.sY.retObj(var4);
   }

   private void NN() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();

      int var2;
      try {
         String var3 = (String)this.pC.ED.getObject(var1);
         var2 = DexUtil.stringToMUTF8(var3, false).length;
      } catch (DexDecEvaluationException var4) {
         throw new cej(var4);
      }

      this.sY.retInt(var2);
   }

   private void np() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      if (var2 != 0L) {
         try {
            this.kS.writeInt(var2, 1);
         } catch (MemoryException var8) {
            throw new cej(var8);
         }
      }

      long var4 = 0L;

      try {
         String var6 = (String)this.pC.ED.getObject(var1);
         if (var6 != null) {
            byte[] var7 = DexUtil.stringToMUTF8(var6);
            var4 = this.A.heapAlloc(var7.length);
            VirtualMemoryUtil.writeBytes(this.kS, var4, var7, 0, var7.length);
         }
      } catch (DexDecEvaluationException var9) {
         throw new cej(var9);
      }

      this.sY.retPtr(var4);
   }

   private void ik() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      long var1 = this.sY.nextAsPtr();
      this.A.heapFree(var1);
   }

   private void aK() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();

      int var2;
      try {
         IDGlobalContext var3 = this.pC.ED.getGlobalContext();
         var2 = this.pC.ED.getArrayObjectLength(var3.createRef(var1));
      } catch (DexDecEvaluationException var4) {
         throw new cej(var4);
      }

      this.sY.retInt(var2);
   }

   private void gR() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsInt();
      int var2 = this.sY.nextAsObj();
      int var3 = this.sY.nextAsObj();
      String var4 = this.pC.wS(var2);
      IDGlobalContext var5 = this.pC.ED.getGlobalContext();
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
         var11 = this.pC.ED.createArray(var6, var1, (List)var7).getObjectReferenceId();
      } catch (DexDecEvaluationException var10) {
         throw new cej(var10);
      }

      this.sY.retObj(var11);
   }

   private void Ig() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();

      int var3;
      try {
         IDGlobalContext var4 = this.pC.ED.getGlobalContext();
         var3 = this.pC.ED.getArrayElement(var4.createRef(var1), var2).getObjectReferenceId();
      } catch (DexDecEvaluationException var5) {
         throw new cej(var5);
      }

      this.sY.retObj(var3);
   }

   private void TV() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();
      int var3 = this.sY.nextAsObj();

      try {
         IDGlobalContext var4 = this.pC.ED.getGlobalContext();
         this.pC.ED.setArrayElement(var4.createRef(var1), var2, var4.createRef(var3));
      } catch (DexDecEvaluationException var5) {
         throw new cej(var5);
      }
   }

   private void A(String var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsInt();
      IJavaTypeFactory var3 = this.pC.ED.getGlobalContext().getTypeFactory();
      IJavaType var4 = var3.createType(var1);

      int var5;
      try {
         var5 = this.pC.ED.createArray(var4, var2, Arrays.asList()).getObjectReferenceId();
      } catch (DexDecEvaluationException var7) {
         throw new cej(var7);
      }

      this.sY.retObj(var5);
   }

   private void pY() {
      this.A("[Z");
   }

   private void l() {
      this.A("[B");
   }

   private void Tr() {
      this.A("[C");
   }

   private void Op() {
      this.A("[S");
   }

   private void yB() {
      this.A("[I");
   }

   private void Mi() {
      this.A("[J");
   }

   private void TD() {
      this.A("[F");
   }

   private void mz() {
      this.A("[D");
   }

   private String pC(Object var1) {
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

   private void kS(String var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      long var3 = this.sY.nextAsPtr();
      if (var3 != 0L) {
         try {
            this.kS.writeInt(var3, 1);
         } catch (MemoryException var12) {
            throw new cej(var12);
         }
      }

      long var5;
      try {
         IDGlobalContext var7 = this.pC.ED.getGlobalContext();
         Object var8 = this.pC.ED.getArrayObject(var7.createRef(var2));
         if (var1 == null) {
            var1 = this.pC(var8);
         }

         switch (var1) {
            case "[Z":
               boolean[] var20 = (boolean[])var8;
               var5 = this.A.heapAlloc(var20.length);
               VirtualMemoryUtil.writeBooleans(this.kS, var5, var20, 0, var20.length);
               break;
            case "[B":
               byte[] var19 = (byte[])var8;
               var5 = this.A.heapAlloc(var19.length);
               VirtualMemoryUtil.writeBytes(this.kS, var5, var19, 0, var19.length);
               break;
            case "[C":
               char[] var18 = (char[])var8;
               var5 = this.A.heapAlloc(var18.length * 2);
               VirtualMemoryUtil.writeChars(this.kS, var5, var18, 0, var18.length);
               break;
            case "[S":
               short[] var17 = (short[])var8;
               var5 = this.A.heapAlloc(var17.length * 2);
               VirtualMemoryUtil.writeShorts(this.kS, var5, var17, 0, var17.length);
               break;
            case "[I":
               int[] var16 = (int[])var8;
               var5 = this.A.heapAlloc(var16.length * 4);
               VirtualMemoryUtil.writeInts(this.kS, var5, var16, 0, var16.length);
               break;
            case "[J":
               long[] var15 = (long[])var8;
               var5 = this.A.heapAlloc(var15.length * 8);
               VirtualMemoryUtil.writeLongs(this.kS, var5, var15, 0, var15.length);
               break;
            case "[F":
               float[] var14 = (float[])var8;
               var5 = this.A.heapAlloc(var14.length * 4);
               VirtualMemoryUtil.writeFloats(this.kS, var5, var14, 0, var14.length);
               break;
            case "[D":
               double[] var11 = (double[])var8;
               var5 = this.A.heapAlloc(var11.length * 8);
               VirtualMemoryUtil.writeDoubles(this.kS, var5, var11, 0, var11.length);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var13) {
         throw new cej(var13);
      }

      this.sY.retPtr(var5);
   }

   private void UJ() {
      this.kS("[Z");
   }

   private void KW() {
      this.kS("[B");
   }

   private void NB() {
      this.kS("[C");
   }

   private void ND() {
      this.kS("[S");
   }

   private void WX() {
      this.kS("[I");
   }

   private void Ck() {
      this.kS("[J");
   }

   private void vU() {
      this.kS("[F");
   }

   private void KM() {
      this.kS("[D");
   }

   private void wS(String var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      long var3 = this.sY.nextAsPtr();
      int var5 = this.sY.nextAsInt();
      if (var5 == 2) {
         this.A.heapFree(var3);
      } else {
         long var6 = var3;

         try {
            IDGlobalContext var8 = this.pC.ED.getGlobalContext();
            Object var9 = this.pC.ED.getArrayObject(var8.createRef(var2));
            if (var1 == null) {
               var1 = this.pC(var9);
            }

            switch (var1) {
               case "[Z":
                  boolean[] var20 = (boolean[])var9;
                  VirtualMemoryUtil.readBooleans(this.kS, var6, var20, 0, var20.length);
                  break;
               case "[B":
                  byte[] var19 = (byte[])var9;
                  VirtualMemoryUtil.readBytes(this.kS, var6, var19, 0, var19.length);
                  break;
               case "[C":
                  char[] var18 = (char[])var9;
                  VirtualMemoryUtil.readChars(this.kS, var6, var18, 0, var18.length);
                  break;
               case "[S":
                  short[] var17 = (short[])var9;
                  VirtualMemoryUtil.readShorts(this.kS, var6, var17, 0, var17.length);
                  break;
               case "[I":
                  int[] var16 = (int[])var9;
                  VirtualMemoryUtil.readInts(this.kS, var6, var16, 0, var16.length);
                  break;
               case "[J":
                  long[] var15 = (long[])var9;
                  VirtualMemoryUtil.readLongs(this.kS, var6, var15, 0, var15.length);
                  break;
               case "[F":
                  float[] var14 = (float[])var9;
                  VirtualMemoryUtil.readFloats(this.kS, var6, var14, 0, var14.length);
                  break;
               case "[D":
                  double[] var12 = (double[])var9;
                  VirtualMemoryUtil.readDoubles(this.kS, var6, var12, 0, var12.length);
                  break;
               default:
                  throw new RuntimeException();
            }
         } catch (DexDecEvaluationException var13) {
            throw new cej(var13);
         }

         if (var5 == 0) {
            this.A.heapFree(var3);
         }
      }
   }

   private void rI() {
      this.wS("[Z");
   }

   private void iX() {
      this.wS("[B");
   }

   private void XZ() {
      this.wS("[C");
   }

   private void jj() {
      this.wS("[S");
   }

   private void jH() {
      this.wS("[I");
   }

   private void uJ() {
      this.wS("[J");
   }

   private void Fx() {
      this.wS("[F");
   }

   private void hB() {
      this.wS("[D");
   }

   private void UT(String var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      int var3 = this.sY.nextAsInt();
      int var4 = this.sY.nextAsInt();
      long var5 = this.sY.nextAsPtr();
      long var7 = var5;

      try {
         IDGlobalContext var9 = this.pC.ED.getGlobalContext();
         Object var10 = this.pC.ED.getArrayObject(var9.createRef(var2));
         switch (var1) {
            case "[Z":
               boolean[] var21 = (boolean[])var10;
               VirtualMemoryUtil.writeBooleans(this.kS, var7, var21, var3, var4);
               break;
            case "[B":
               byte[] var20 = (byte[])var10;
               VirtualMemoryUtil.writeBytes(this.kS, var7, var20, var3, var4);
               break;
            case "[C":
               char[] var19 = (char[])var10;
               VirtualMemoryUtil.writeChars(this.kS, var7, var19, var3, var4);
               break;
            case "[S":
               short[] var18 = (short[])var10;
               VirtualMemoryUtil.writeShorts(this.kS, var7, var18, var3, var4);
               break;
            case "[I":
               int[] var17 = (int[])var10;
               VirtualMemoryUtil.writeInts(this.kS, var7, var17, var3, var4);
               break;
            case "[J":
               long[] var16 = (long[])var10;
               VirtualMemoryUtil.writeLongs(this.kS, var7, var16, var3, var4);
               break;
            case "[F":
               float[] var15 = (float[])var10;
               VirtualMemoryUtil.writeFloats(this.kS, var7, var15, var3, var4);
               break;
            case "[D":
               double[] var13 = (double[])var10;
               VirtualMemoryUtil.writeDoubles(this.kS, var7, var13, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var14) {
         throw new cej(var14);
      }
   }

   private void gW() {
      this.UT("[Z");
   }

   private void pP() {
      this.UT("[B");
   }

   private void sd() {
      this.UT("[C");
   }

   private void OD() {
      this.UT("[S");
   }

   private void hq() {
      this.UT("[I");
   }

   private void sR() {
      this.UT("[J");
   }

   private void ib() {
      this.UT("[F");
   }

   private void zJ() {
      this.UT("[D");
   }

   private void E(String var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      int var3 = this.sY.nextAsInt();
      int var4 = this.sY.nextAsInt();
      long var5 = this.sY.nextAsPtr();
      long var7 = var5;

      try {
         IDGlobalContext var9 = this.pC.ED.getGlobalContext();
         Object var10 = this.pC.ED.getArrayObject(var9.createRef(var2));
         switch (var1) {
            case "[Z":
               boolean[] var21 = (boolean[])var10;
               VirtualMemoryUtil.readBooleans(this.kS, var7, var21, var3, var4);
               break;
            case "[B":
               byte[] var20 = (byte[])var10;
               VirtualMemoryUtil.readBytes(this.kS, var7, var20, var3, var4);
               break;
            case "[C":
               char[] var19 = (char[])var10;
               VirtualMemoryUtil.readChars(this.kS, var7, var19, var3, var4);
               break;
            case "[S":
               short[] var18 = (short[])var10;
               VirtualMemoryUtil.readShorts(this.kS, var7, var18, var3, var4);
               break;
            case "[I":
               int[] var17 = (int[])var10;
               VirtualMemoryUtil.readInts(this.kS, var7, var17, var3, var4);
               break;
            case "[J":
               long[] var16 = (long[])var10;
               VirtualMemoryUtil.readLongs(this.kS, var7, var16, var3, var4);
               break;
            case "[F":
               float[] var15 = (float[])var10;
               VirtualMemoryUtil.readFloats(this.kS, var7, var15, var3, var4);
               break;
            case "[D":
               double[] var13 = (double[])var10;
               VirtualMemoryUtil.readDoubles(this.kS, var7, var13, var3, var4);
               break;
            default:
               throw new RuntimeException();
         }
      } catch (DexDecEvaluationException var14) {
         throw new cej(var14);
      }
   }

   private void aU() {
      this.E("[Z");
   }

   private void dC() {
      this.E("[B");
   }

   private void fN() {
      this.E("[C");
   }

   private void dW() {
      this.E("[S");
   }

   private void dE() {
      this.E("[I");
   }

   private void xy() {
      this.E("[J");
   }

   private void tR() {
      this.E("[F");
   }

   private void lO() {
      this.E("[D");
   }

   private void ll() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      long var2 = this.sY.nextAsPtr();
      int var4 = this.sY.nextAsInt();
      String var5 = this.pC.wS(var1);
      VMReader var6 = new VMReader(this.kS);
      long var7 = var2;
      int var9 = var4;

      for (int var10 = 0; var10 < var9; var10++) {
         VMReader.Buf var11 = var6.read(var7, this.pC.UO);
         String var12 = cek.pC(this.kS, var11.getPtr("name"));
         String var13 = cek.pC(this.kS, var11.getPtr("signature"));
         long var14 = var11.getPtr("fnPtr");
         this.pC.pC(var5, var12, var13, var14);
         var7 += this.pC.UO.getSize();
      }

      this.sY.retInt(0);
   }

   private void KC() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      String var2 = this.pC.wS(var1);
      this.pC.wS(var2);
      this.sY.retInt(0);
   }

   private void cH() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      this.sY.retInt(0);
   }

   private void bm() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
      this.sY.retInt(0);
   }

   private void hE() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      this.A.writePointer(var1, this.pC.rl);
      this.sY.retInt(0);
   }

   private void vr() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();
      int var3 = this.sY.nextAsInt();
      long var4 = this.sY.nextAsPtr();

      String var6;
      try {
         var6 = (String)this.pC.ED.getObject(var1);
      } catch (DexDecEvaluationException var9) {
         throw new cej(var9);
      }

      String var7 = var6.substring(var2, var2 + var3);
      char[] var8 = var7.toCharArray();
      VirtualMemoryUtil.writeChars(this.kS, var4, var8, 0, var8.length);
   }

   private void LV() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      int var2 = this.sY.nextAsInt();
      int var3 = this.sY.nextAsInt();
      long var4 = this.sY.nextAsPtr();

      String var6;
      try {
         var6 = (String)this.pC.ED.getObject(var1);
      } catch (DexDecEvaluationException var9) {
         throw new cej(var9);
      }

      String var7 = var6.substring(var2, var2 + var3);
      byte[] var8 = DexUtil.stringToMUTF8(var7, false);
      VirtualMemoryUtil.writeBytes(this.kS, var4, var8, 0, var8.length);
   }

   private void Yi() {
      this.kS(null);
   }

   private void dF() {
      this.wS(null);
   }

   private void Jq() {
      this.AQ();
   }

   private void Fz() {
      this.BX();
   }

   private void mr() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      this.sY.retObj(var1);
   }

   private void ct() {
      this.sY.skipJniEnvPtr();
      this.sY.nextAsObj();
   }

   private void wj() {
      this.sY.skipJniEnvPtr();
      byte var1 = 0;
      if (this.pC.Er != 0) {
         var1 = 1;
      }

      this.sY.retInt(var1);
   }

   private Couple pC(int var1, ByteBuffer var2, long var3, int var5) {
      long var6 = var3 + var5;
      Object[] var10000 = new Object[]{var1, var3, var6};
      this.A.getState().registerHooks(new ceh(this, var3, var6, var2), false);
      Couple var8 = new Couple(var3, var5);
      this.ys.put(var1, var8);
      return var8;
   }

   private void kB() {
      this.sY.skipJniEnvPtr();
      long var1 = this.sY.nextAsPtr();
      int var3 = (int)this.sY.nextAsLong();
      if (var1 != 0L && var3 < 104857600) {
         ByteBuffer var4 = ByteBuffer.allocateDirect(var3);
         byte[] var5 = new byte[var3];

         try {
            this.kS.read(var1, var3, var5, 0, true);
         } catch (MemoryException var7) {
            this.sY.retObj(0);
            return;
         }

         var4.put(0, var5);
         int var6 = this.pC.ED.registerObject(var4).getObjectReferenceId();
         this.pC(var6, var4, var1, var3);
         this.sY.retObj(var6);
      } else {
         this.sY.retObj(0);
      }
   }

   private void pC(boolean var1) {
      this.sY.skipJniEnvPtr();
      int var2 = this.sY.nextAsObj();
      ByteBuffer var3 = null;

      try {
         var3 = (ByteBuffer)this.pC.ED.getObject(var2);
      } catch (Exception var8) {
      }

      if (var3 != null && var3.isDirect()) {
         Couple var4 = (Couple)this.ys.get(var2);
         if (var4 == null) {
            int var5 = var3.capacity();
            long var6 = this.A.heapAlloc(var5);
            var4 = this.pC(var2, var3, var6, var5);
         }

         if (var1) {
            this.sY.retPtr((Long)var4.getFirst());
         } else {
            this.sY.retLong(((Integer)var4.getSecond()).intValue());
         }
      } else {
         this.sY.retPtr(0L);
      }
   }

   private void rB() {
      this.pC(true);
   }

   private void zL() {
      this.pC(false);
   }

   private void oc() {
      this.sY.skipJniEnvPtr();
      int var1 = this.sY.nextAsObj();
      byte var2 = 0;

      try {
         if (this.pC.ED.getObject(var1) != null) {
            var2 = 2;
         }
      } catch (DexDecEvaluationException var3) {
      }

      this.sY.retInt(var2);
   }
}
