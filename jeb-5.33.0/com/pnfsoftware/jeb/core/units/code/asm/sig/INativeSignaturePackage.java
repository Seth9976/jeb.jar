package com.pnfsoftware.jeb.core.units.code.asm.sig;

import java.util.List;

public interface INativeSignaturePackage {
   List getSignatures();

   NativeSignaturePackageMetadata getMetadata();

   int count();
}
