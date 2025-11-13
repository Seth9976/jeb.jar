package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public enum CodeGapAnalysisStyle {
   PROLOGUES_ONLY,
   LINEAR_SWEEP,
   NONE;
}
