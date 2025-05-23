import { object, string } from "yup";
import { FunctionHandlerError, FunctionHandlerReturnType } from "..";
import { getGeocoding } from "../../../services/maps";
import { getWeather } from "../../../services/weather";
import { addHours, format } from "date-fns";
import { DavisToolContext } from "src/davis";

const payloadSchema = object({
  location: string(),
});

export const handleGetWeather = async (
  payload: string,
  context: DavisToolContext
): FunctionHandlerReturnType => {
  let location: string | undefined;
  try {
    const parsedPayload = await payloadSchema.validate(JSON.parse(payload));
    location = parsedPayload.location;
  } catch {
    throw new FunctionHandlerError("Weather search location is invalid.");
  }

  let { latitude, longitude } = context.data;

  if (location) {
    const geocodeLocation = await getGeocoding(location);
    latitude = geocodeLocation.latitude;
    longitude = geocodeLocation.longitude;
  }

  const tempf = (temp: number) => `${temp.toFixed(0)} °F`;
  const humidityf = (humidity: number) => `${humidity.toFixed(0)}%`;
  const speedf = (speed: number) => `${speed.toFixed(0)} mph`;
  const timef = (time: Date) => format(addHours(time, context.time.tzOffset), "h:mm aaa");
  const percentf = (percent: number) => `${percent * 100}%`;

  const weather = await getWeather(latitude, longitude);

  return JSON.stringify({
    low: tempf(weather.low),
    high: tempf(weather.high),
    probOfPercip: percentf(weather.probOfPercip),
    currentTemp: tempf(weather.currentTemp),
    currentHumidity: humidityf(weather.currentHumidity),
    currentUvi: weather.currentUvi,
    currentWindSpeed: speedf(weather.currentWindSpeed),
    sunrise: timef(weather.sunrise),
    sunset: timef(weather.sunset),
  });
};
