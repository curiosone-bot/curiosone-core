package com.github.bot.curiosone.core.nlp.tokenizer;

public enum PointerT {
	/**
	 * Lexical/semantic link  
	 * Cfr. https://web.stanford.edu/class/cs276a/projects/docs/jwnl/javadoc/net/didion/jwnl/data/PointerType.html
	 * @author riva
	 */
	ANTONYM, 
	ATTRIBUTE,
	CAUSE,
	DERIVED,
	ENTAILED_BY,
	ENTAILMENT,
	HYPERNYM,
	HYPONYM,
	MEMBER_HOLONYM,
	MEMBER_MERONYM,
	PART_HOLONYM,
	PART_MERONYM,
	PARTICIPLE_OF,
	SEE_ALSO,
	SIMILAR_TO,
	SUBSTANCE_HOLONYM,
	SUBSTANCE_MERONYM,
	VERB_GROUP
}
