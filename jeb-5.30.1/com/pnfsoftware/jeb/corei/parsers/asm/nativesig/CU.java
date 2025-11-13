package com.pnfsoftware.jeb.corei.parsers.asm.nativesig;

import com.pnfsoftware.jeb.core.units.code.asm.sig.INativeSignature;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignaturePackageEntry;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;
import java.util.Set;

@Ser
public interface CU extends INativeSignature {
   List q();

   Set RF();

   Set xK();

   Set Dw();

   void q(NativeSignaturePackageEntry var1);
}
