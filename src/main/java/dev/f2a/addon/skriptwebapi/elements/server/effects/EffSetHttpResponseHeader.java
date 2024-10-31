package dev.f2a.addon.skriptwebapi.elements.server.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.net.httpserver.HttpExchange;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

@Name("Set http response header")
@Description("Set HTTP response header to as you specified.")
@Examples("on http request received:\n" +
        "    set {_request} to event-httpexchange\n" +
        "    set http response {_request}'s header \"Custom\" to \"Custom header\"")
@Since("0.1.0")
public class EffSetHttpResponseHeader extends Effect {

    static {
        Skript.registerEffect(
                EffSetHttpResponseHeader.class,
                "[skweapi] set [the] (http|api) response %httpexchange%['s] header %string% to %string%"
        );
    }

    private Expression<HttpExchange> httpExchange;
    private Expression<String>  headerKey;
    private Expression<String>  headerValue;

    @Override
    protected void execute(Event e) {
        HttpExchange exchange = httpExchange.getSingle(e);
        String key = headerKey.getSingle(e);
        String value = headerValue.getSingle(e);

        if(exchange == null) {
            Skript.error("Provided HttpExchange instance is null!");
            return;
        }

        if(key == null || key.isEmpty()) {
            Skript.error("Header key is should not be blank!");
            return;
        }

        exchange.getResponseHeaders().set(key, value);
    }

    @Override
    public String toString(@Nullable Event e, boolean debug) {
        return "";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        httpExchange = (Expression<HttpExchange>) exprs[0];
        headerKey = (Expression<String>) exprs[1];
        headerValue = (Expression<String>) exprs[2];
        return true;
    }
}
