package com.neil.antlr4;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class AntlrTest {
    public static void main(String[] args) {
        String expr = "a+(b*3)";
        //对每一个输入的字符串，构造一个 ANTLRStringStream 流 in
        ANTLRInputStream in = new ANTLRInputStream(expr);

        //用 in 构造词法分析器 lexer，词法分析的作用是产生记号
        DemoLexer lexer = new DemoLexer(in);

        //用词法分析器 lexer 构造一个记号流 tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        //再使用 tokens 构造语法分析器 parser,至此已经完成词法分析和语法分析的准备工作
        DemoParser parser = new DemoParser(tokens);

        //最终调用语法分析器的规则 prog，完成对表达式的验证
        parser.prog();

        System.out.println();
    }
}
